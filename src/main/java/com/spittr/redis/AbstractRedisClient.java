package com.spittr.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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
	private JedisPool jedisPool;
	
	/**
	 * 获取redis连接
	 * 
	 * @return Jedis连接
	 */
	public Jedis getJedisIntance() {
		try {
			return jedisPool.getResource();
		} catch (Exception e) {
			LOG.error("error to get JedisPool", e);
		}
		
		return null;
	}
	
	/**
	 * 关闭redis连接
	 * 
	 * @param jedis redis连接
	 */
	public void closeJedis(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}
	
}
