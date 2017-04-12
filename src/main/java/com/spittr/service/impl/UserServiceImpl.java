package com.spittr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spittr.mapper.UserMapper;
import com.spittr.redis.UserRedisClient;
import com.spittr.service.UserService;
import com.spittr.utils.SpittrException;
import com.spittr.utils.constant.CodeConstant;
import com.theft.code.utils.date.DateCalculateUtil;
import com.theft.code.utils.encrypt.EncryptUtil;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRedisClient userRedis;

	@Override
	public long register(String nickname, String password, int gender, String location, String profile, String phoneNum,
			String birthDay) throws SpittrException {
		Date now = new Date();
		password = new EncryptUtil(password, null, null).encodeBySalt();
		if (password == null) {
			LOG.error("error to encrypt password");
			throw new SpittrException("error to encrypt password", CodeConstant.EXCEPTION_SERVICE);
		}
		// 数据库插入用户注册信息
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("nickname", nickname);
		param.put("password", password);
		param.put("gender", gender);
		param.put("location", location);
		param.put("profile", profile);
		param.put("phoneNum", phoneNum);
		param.put("birthDay", birthDay);
		param.put("registeredTime", now);
		try {
			// 向mysql插入注册用户信息
			userMapper.register(param);
			long userId = (long) param.get("userId");
			if (userId > 0) {
				// 获取出生年
				int birthYear = Integer.valueOf(birthDay.split("-")[0]);
				// 计算年龄
				int age = DateCalculateUtil.getCurrentYear(now) - birthYear + 1;
				// redis插入用户信息
				userRedis.saveUserInfo(userId, nickname, gender, location, profile, phoneNum, age);

				return userId;
			}
		} catch (Exception e) {
			LOG.error("error execute userMapper.register", e);
			throw new SpittrException("error execute userMapper.register", e, CodeConstant.EXCEPTION_SERVICE);
		}

		return 0;
	}

	@Override
	public boolean isExistNickname(String nickname) throws SpittrException {
		try {
			return userMapper.getUserCountByNickname(nickname) > 0;
		} catch (Exception e) {
			LOG.error("error execute userMapper.getUserCountByNickname", e);
			throw new SpittrException("error execute userMapper.getUserCountByNickname", e,
					CodeConstant.EXCEPTION_SERVICE);
		}
	}

	@Override
	public boolean isExistPhone(String phoneNum) throws SpittrException {
		try {
			return userMapper.getUserCountByPhoneNum(phoneNum) > 0;
		} catch (Exception e) {
			LOG.error("error execute userMapper.getUserCountByPhoneNum", e);
			throw new SpittrException("error execute userMapper.getUserCountByPhoneNum", e,
					CodeConstant.EXCEPTION_SERVICE);
		}
	}

}
