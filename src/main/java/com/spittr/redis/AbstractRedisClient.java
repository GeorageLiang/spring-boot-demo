package com.spittr.redis;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.spittr.utils.constant.JedisResourceConstant;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis客户端连接类
 * 
 * @author chufei
 * @date 2017年4月11日
 */
public abstract class AbstractRedisClient {

	private static final Logger LOG = Logger.getLogger(AbstractRedisClient.class);

	@Autowired
	private Map<String, JedisPool> jedisPoolMap;

	/**
	 * 获取redis连接
	 * 
	 * @return Jedis连接
	 */
	protected Jedis getJedisIntance(String resourceName) {
		try {
			return jedisPoolMap.get(JedisResourceConstant.JEDISPOOL_PREFIX + "_" + resourceName).getResource();
		} catch (Exception e) {
			LOG.error("error to get JedisPool", e);
		}

		return null;
	}

	/**
	 * 关闭redis连接
	 * 
	 * @param jedis
	 *            redis连接
	 */
	protected void closeJedis(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

}
