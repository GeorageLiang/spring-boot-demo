package com.spittr.config.lettuce;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

/**
 * 使用Lettuce Client的redis客户端连接配置
 * @author chufei
 * 2018年7月4日
 */
//@Configuration
public class LettuceRedisConfiguration {

	@Autowired
	private Environment environment;
	
	//@Bean("lettuceClient")
	public RedisClient initLettuceRedisClient() {
		RedisURI redisURI = new RedisURI();
		redisURI.setHost(environment.getProperty("spring.redis.lettuce.user.host", String.class));
		redisURI.setPassword(environment.getProperty("spring.redis.lettuce.user.password", String.class));
		redisURI.setPort(environment.getProperty("spring.redis.lettuce.user.port", Integer.class));
		redisURI.setDatabase(environment.getProperty("spring.redis.lettuce.user.database", Integer.class));
		redisURI.setTimeout(environment.getProperty("spring.redis.lettuce.user.timeout", Duration.class));

		return RedisClient.create(redisURI);
	}
	
}
