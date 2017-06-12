package com.spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis连接配置类
 * @author chufei
 * @date 2017年6月12日
 */
@Configuration
@PropertySource("classpath:properties/redis.properties")
public class JedisPoolConfiguration {

	@Autowired
	private Environment env;
	
	@Bean(name="jedisPool")
	public JedisPool jedisPool() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxWaitMillis(env.getProperty("spring.redis.pool.max-wait", Integer.class));
		jedisPoolConfig.setMaxIdle(env.getProperty("spring.redis.pool.max-idle", Integer.class));
		jedisPoolConfig.setMinIdle(env.getProperty("spring.redis.pool.min-idle", Integer.class));
		
		String host = env.getProperty("spring.redis.host");
		int port = env.getProperty("spring.redis.port", Integer.class);
		int timeout = env.getProperty("spring.redis.timeout", Integer.class);
		String password = env.getProperty("spring.redis.password");
		int database = env.getProperty("spring.redis.database", Integer.class);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
		return jedisPool;
	}
	
}
