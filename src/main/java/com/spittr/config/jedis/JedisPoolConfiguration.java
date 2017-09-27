package com.spittr.config.jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 非分片式jedis连接池配置类
 * 
 * @author chufei
 * @date 2017年6月12日
 */
@Configuration
public class JedisPoolConfiguration {

	@Autowired
	private List<JedisConfig> jedisConfigs;
	
	/**
	 * 初始化多redis连接池
	 * 
	 * @return
	 */
	@Bean("jedisPoolMap")
	public Map<String, JedisPool> initJedisPool() {
		Map<String, JedisPool> map = new HashMap<>();
		for (JedisConfig jedisConfig : jedisConfigs) {
			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			jedisPoolConfig.setMaxWaitMillis(jedisConfig.getPoolMaxWait());
			jedisPoolConfig.setMaxIdle(jedisConfig.getPoolMaxIdle());
			jedisPoolConfig.setMinIdle(jedisConfig.getPoolMinIdle());
			jedisPoolConfig.setMaxTotal(jedisConfig.getPoolMaxActive());

			String password = jedisConfig.getPassword();
			JedisPool jedisPool = new JedisPool(jedisPoolConfig, jedisConfig.getHost(), jedisConfig.getPort(), jedisConfig.getTimeout(),
					password.isEmpty() ? null : password, jedisConfig.getDatabase());
			map.put(jedisConfig.getResource(), jedisPool);
		}
		return map;
	}
	
}
