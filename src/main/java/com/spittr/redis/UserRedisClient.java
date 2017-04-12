package com.spittr.redis;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

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

	public void saveUserInfo(long userId, String nickname, int gender, String location, String profile, String phoneNum,
			int age) {
		Jedis jedis = null;
		try {
			jedis = getJedisIntance();
			String key = String.format(USER_INFO_KEY, userId);
			Map<String, String> hash = new HashMap<String, String>();
			hash.put("userId", String.valueOf(userId));
			hash.put("nickname", nickname);
			hash.put("gender", String.valueOf(gender));
			hash.put("location", location);
			hash.put("profile", profile);
			hash.put("phoneNum", phoneNum);
			hash.put("age", String.valueOf(age));
			jedis.hmset(key, hash);
			// 缓存用户信息30天
			jedis.expire(key, 60 * 60 * 24 * 30);
		} catch (Exception e) {
			LOG.error("error save user_info_" + userId, e);
		} finally {
			closeJedis(jedis);
		}
	}

}
