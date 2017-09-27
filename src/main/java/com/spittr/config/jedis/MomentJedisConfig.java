package com.spittr.config.jedis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 动态相关redis配置
 * 
 * @author chufei
 * @date 2017年9月26日
 */
@Component
@ConfigurationProperties(prefix = MomentJedisConfig.PREFIX)
public class MomentJedisConfig extends JedisConfig {

	/**
	 * redis连接配置属性key前缀
	 */
	public static final String PREFIX = "jedis.moment";
	
	/**
	 * redis连接配置属性jedis.moment.resource值
	 */
	public static final String RESOURCE_NAME = "momentResource";

}
