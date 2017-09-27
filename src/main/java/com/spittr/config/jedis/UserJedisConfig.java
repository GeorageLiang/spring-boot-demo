package com.spittr.config.jedis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用户相关redis配置
 * 
 * @author chufei
 * @date 2017年9月26日
 */
@Component
@ConfigurationProperties(prefix = UserJedisConfig.PREFIX)
public class UserJedisConfig extends JedisConfig {

	/**
	 * redis连接配置属性key前缀
	 */
	public static final String PREFIX = "jedis.user";

	/**
	 * redis连接配置属性jedis.user.resource值
	 */
	public static final String RESOURCE_NAME = "userResource";
	
}
