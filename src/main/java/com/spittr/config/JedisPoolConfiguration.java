package com.spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.spittr.utils.constant.JedisResourceConstant;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 非分片式jedis连接池配置类
 * 
 * @author chufei
 * @date 2017年6月12日
 */
@Configuration
@PropertySource("classpath:properties/redis.properties")
public class JedisPoolConfiguration {

	/**
	 * 服务器地址
	 */
	private static final String HOST_PREFIX = "spring.redis.host.";

	/**
	 * 服务器连接端口
	 */
	private static final String PORT_PREFIX = "spring.redis.port.";

	/**
	 * 服务器连接密码
	 */
	private static final String PASSWORD_PREFIX = "spring.redis.password.";

	/**
	 * 连接超时时间
	 */
	private static final String TIMEOUT_PREFIX = "spring.redis.timeout.";

	/**
	 * 数据库索引
	 */
	private static final String DATABASE_PREFIX = "spring.redis.database.";

	/**
	 * 连接池最大连接数
	 */
	private static final String MAXACTIVE_PREFIX = "spring.redis.pool.max-active.";

	/**
	 * 连接池最大阻塞等待时间
	 */
	private static final String MAXWAIT_PREFIX = "spring.redis.pool.max-wait.";

	/**
	 * 连接池中的最大空闲连接
	 */
	private static final String MAXIDLE_PREFIX = "spring.redis.pool.max-idle.";

	/**
	 * 连接池中的最小空闲连接
	 */
	private static final String MINIDLE_PREFIX = "spring.redis.pool.min-idle.";

	@Autowired
	private Environment env;

	/**
	 * 根据类型初始化jedis连接池
	 * 
	 * @param resourceName
	 *            redis连接类型
	 * @return JedisPool 连接池
	 */
	private JedisPool initJedisPool(String resourceName) {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxWaitMillis(env.getProperty(MAXWAIT_PREFIX + resourceName, Integer.class));
		jedisPoolConfig.setMaxIdle(env.getProperty(MAXIDLE_PREFIX + resourceName, Integer.class));
		jedisPoolConfig.setMinIdle(env.getProperty(MINIDLE_PREFIX + resourceName, Integer.class));
		jedisPoolConfig.setMaxTotal(env.getProperty(MAXACTIVE_PREFIX + resourceName, Integer.class));

		String host = env.getProperty(HOST_PREFIX + resourceName);
		int port = env.getProperty(PORT_PREFIX + resourceName, Integer.class);
		String password = env.getProperty(PASSWORD_PREFIX + resourceName);
		int database = env.getProperty(DATABASE_PREFIX + resourceName, Integer.class);
		int timeout = env.getProperty(TIMEOUT_PREFIX + resourceName, Integer.class);
		return new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
	}

	@Bean(name = JedisResourceConstant.JEDISPOOL_PREFIX + "_" + JedisResourceConstant.USER_RESOURCE)
	public JedisPool userJedisPool() {
		return initJedisPool(JedisResourceConstant.USER_RESOURCE);
	}

	@Bean(name = JedisResourceConstant.JEDISPOOL_PREFIX + "_" + JedisResourceConstant.MOMENTS_RESOURCE)
	public JedisPool momentsJedisPool() {
		return initJedisPool(JedisResourceConstant.MOMENTS_RESOURCE);
	}

}
