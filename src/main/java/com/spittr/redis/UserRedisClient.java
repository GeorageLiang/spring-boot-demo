package com.spittr.redis;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.spittr.config.jedis.UserJedisConfig;
import com.spittr.model.User;
import com.spittr.utils.ConverUtil;

import redis.clients.jedis.Jedis;

/**
 * 用户相关redis操作
 * 
 * @author chufei
 * @date 2017年4月11日
 */
@Repository
public class UserRedisClient extends AbstractRedisClient {

	private static final Logger LOG = Logger.getLogger(UserRedisClient.class);

	private static final String USER_INFO_KEY = "user_info_%s";

	private Jedis getJedisIntance() {
		return getJedisIntanceByResourceName(UserJedisConfig.RESOURCE_NAME);
	}
	
	/**
	 * 保存用户信息缓存
	 * 
	 * @param userId
	 *            用户id
	 * @param nickname
	 *            昵称
	 * @param gender
	 *            性别
	 * @param location
	 *            所在地
	 * @param profile
	 *            个人简介
	 * @param phoneNum
	 *            手机号
	 * @param age
	 *            年龄
	 * @param birthDay
	 *            出生日期
	 */
	public void saveUserInfo(long userId, String nickname, int gender, String location, String profile, String phoneNum,
			int age, String birthDay) {
		Jedis jedis = null;
		try {
			jedis = getJedisIntance();
			String key = String.format(USER_INFO_KEY, userId);
			Map<String, String> hash = new HashMap<String, String>(16);
			hash.put("userId", String.valueOf(userId));
			hash.put("nickname", nickname);
			hash.put("gender", String.valueOf(gender));
			hash.put("location", location);
			hash.put("profile", profile);
			hash.put("phoneNum", phoneNum);
			hash.put("birthDay", birthDay);
			hash.put("age", String.valueOf(age));
			jedis.hmset(key, hash);
			// 缓存用户信息30天
			jedis.expire(key, 30 * 24 * 60 * 60);
		} catch (Exception e) {
			LOG.error("error save user_info_" + userId, e);
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 更新用户信息缓存
	 * 
	 * @param userId
	 *            用户id
	 * @param nickname
	 *            昵称
	 * @param gender
	 *            性别
	 * @param location
	 *            所在地
	 * @param profile
	 *            个人简介
	 * @param phoneNum
	 *            手机号
	 * @param age
	 *            年龄
	 * @param birthDay
	 *            出生日期
	 */
	public void updateUserInfo(long userId, String nickname, int gender, String location, String profile,
			String phoneNum, int age, String birthDay) {
		Jedis jedis = null;
		try {
			jedis = getJedisIntance();
			String key = String.format(USER_INFO_KEY, userId);
			Map<String, String> hash = jedis.hgetAll(key);
			if (hash != null) {
				hash.put("nickname", nickname);
				hash.put("gender", String.valueOf(gender));
				hash.put("location", location);
				hash.put("profile", profile);
				hash.put("phoneNum", phoneNum);
				hash.put("birthDay", birthDay);
				hash.put("age", String.valueOf(age));
				jedis.hmset(key, hash);
				// 缓存用户信息30天
				jedis.expire(key, 30 * 24 * 60 * 60);
			} else {
				saveUserInfo(userId, nickname, gender, location, profile, phoneNum, age, birthDay);
			}
		} catch (Exception e) {
			LOG.error("error update user_info_" + userId, e);
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 获取用户信息缓存
	 * 
	 * @param userId
	 *            用户id
	 * @return 返回用户信息hash
	 */
	public User getUserInfo(long userId) {
		Jedis jedis = null;
		Map<String, String> userInfo = new HashMap<String, String>(16);
		try {
			jedis = getJedisIntance();
			String key = String.format(USER_INFO_KEY, userId);
			userInfo = jedis.hgetAll(key);
			return ConverUtil.map2Object(userInfo, User.class);
		} catch (Exception e) {
			LOG.error("error get user_info_" + userId, e);
		} finally {
			closeJedis(jedis);
		}

		return null;
	}

}
