package com.spittr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spittr.exception.SpittrException;
import com.spittr.mapper.spittr.master.LoginInfoMapper;
import com.spittr.mapper.spittr.master.UserMapper;
import com.spittr.model.User;
import com.spittr.redis.UserRedisClient;
import com.spittr.service.UserService;
import com.spittr.utils.LogUtil;
import com.spittr.utils.constant.CodeConstant;
import com.spittr.utils.convert.UserConvert;
import com.theft.code.utils.date.DateCalculateUtil;
import com.theft.code.utils.encrypt.EncryptUtil;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private LoginInfoMapper loginInfoMapper;

	@Autowired
	private UserRedisClient userRedis;

	@Override
	public long register(String nickname, String password, int gender, String location, String profile, String phoneNum,
			String birthDay, int registeredPlatform) {
		Date now = new Date();
		password = new EncryptUtil(password, null, null).encodeBySalt();
		if (password == null) {
			LOG.error("error to encrypt password");
			throw new SpittrException("error to encrypt password", CodeConstant.EXCEPTION_SERVICE);
		}
		// 数据库插入用户注册信息
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nickname", nickname);
		params.put("password", password);
		params.put("gender", gender);
		params.put("location", location);
		params.put("profile", profile);
		params.put("phoneNum", phoneNum);
		params.put("birthDay", birthDay);
		params.put("registeredTime", now);
		params.put("registeredPlatform", registeredPlatform);
		int age = getAge(birthDay);
		params.put("age", age);
		try {
			// 向mysql插入注册用户信息
			userMapper.register(params);
			long userId = (long) params.get("userId");
			if (userId > 0) {
				// redis插入用户信息
				userRedis.saveUserInfo(userId, nickname, gender, location, profile, phoneNum, age, birthDay);

				return userId;
			}
		} catch (Exception e) {
			LOG.error("error execute userMapper.register, register-param: " + params.toString(), e);
			throw new SpittrException("error execute userMapper.register", e, CodeConstant.EXCEPTION_SERVICE);
		}

		return 0;
	}

	@Override
	public User login(long userId, String phoneNum, String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("password", new EncryptUtil(password, null, null).encodeBySalt());
		if (userId > 0) {
			param.put("userId", userId);
			try {
				userId = userMapper.loginByUserId(param) != null ? userId : 0;
			} catch (Exception e) {
				LOG.error("error execute userMapper.loginByUserId, login-param: " + param.toString(), e);
				throw new SpittrException("error execute userMapper.loginByUserId", e, CodeConstant.EXCEPTION_SERVICE);
			}
		} else {
			param.put("phoneNum", phoneNum);
			try {
				Long _userId = userMapper.loginByPhone(param);
				userId = _userId != null ? _userId : 0;
			} catch (Exception e) {
				LOG.error("error execute userMapper.loginByPhone, login-param: " + param.toString(), e);
				throw new SpittrException("error execute userMapper.loginByPhone", e, CodeConstant.EXCEPTION_SERVICE);
			}
		}

		if (userId > 0) {
			User user = getUserInfoById(userId);
			if (user != null) {
				// TODO 设置用户登录token

				return user;
			}
		}

		return null;
	}

	@Override
	public void loginLog(long userId, String token, String ip, int platform) {
		Date now = new Date();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("token", token);
		params.put("ip", ip);
		params.put("platform", platform);
		params.put("loginTime", now);
		try {
			loginInfoMapper.loginLog(params);
			LogUtil.loginInfoLog(userId, token, ip, platform, now);
		} catch (Exception e) {
			LogUtil.loginInfoErrorLog(userId, token, ip, platform, now);
		}
	}

	@Override
	public boolean isExistNickname(String nickname) {
		try {
			return userMapper.getUserCountByNickname(nickname) != null;
		} catch (Exception e) {
			LOG.error("error execute userMapper.getUserCountByNickname, nickname: " + nickname, e);
			throw new SpittrException("error execute userMapper.getUserCountByNickname", e,
					CodeConstant.EXCEPTION_SERVICE);
		}
	}

	@Override
	public boolean isExistPhone(String phoneNum) {
		try {
			return userMapper.getUserCountByPhoneNum(phoneNum) != null;
		} catch (Exception e) {
			LOG.error("error execute userMapper.getUserCountByPhoneNum, phoneNum: " + phoneNum, e);
			throw new SpittrException("error execute userMapper.getUserCountByPhoneNum", e,
					CodeConstant.EXCEPTION_SERVICE);
		}
	}

	@Override
	public User getUserInfoById(long userId) {
		try {
			Map<String, String> userInfo = userRedis.getUserInfo(userId);
			if (userInfo.isEmpty()) {
				User user = userMapper.getUserInfoById(userId);
				if (user != null) {
					userRedis.saveUserInfo(userId, user.getNickname(), user.getGender(), user.getLocation(),
							user.getProfile(), user.getPhoneNum(), getAge(user.getBirthDay()), user.getBirthDay());
					return user;
				}
				return null;
			}

			return UserConvert.map2User(userInfo);
		} catch (Exception e) {
			LOG.error("error execute userMapper.getUserInfoById, userId: " + userId, e);
			throw new SpittrException("error execute userMapper.getUserInfoById", e, CodeConstant.EXCEPTION_SERVICE);
		}
	}

	/**
	 * 计算用户年龄
	 * 
	 * @param birthDay
	 *            出生日期
	 * @return 当前年龄
	 */
	private int getAge(String birthDay) {
		// 获取出生年
		int birthYear = Integer.valueOf(birthDay.split("-")[0]);
		// 计算年龄
		int age = DateCalculateUtil.getCurrentYear(new Date()) - birthYear + 1;
		return age;
	}

}
