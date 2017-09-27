package com.spittr.config.jedis;

/**
 * redis公共配置
 * 
 * @author chufei
 * @date 2017年9月26日
 */
public class JedisConfig {

	/**
	 * 资源链接名
	 */
	private String resource;

	/**
	 * Redis数据库索引（默认为0）
	 */
	private int database;

	/**
	 * Redis服务器地址
	 */
	private String host;

	/**
	 * Redis服务器连接密码（默认为空）
	 */
	private String password;

	/**
	 * Redis服务器连接端口
	 */
	private int port;

	/**
	 * 连接超时时间（毫秒）
	 */
	private int timeout;

	/**
	 * 连接池最大连接数（使用负值表示没有限制）
	 */
	private int poolMaxActive;

	/**
	 * 连接池最大阻塞等待时间（使用负值表示没有限制）
	 */
	private int poolMaxWait;

	/**
	 * 连接池中的最大空闲连接
	 */
	private int poolMaxIdle;

	/**
	 * 连接池中的最小空闲连接
	 */
	private int poolMinIdle;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getPoolMaxActive() {
		return poolMaxActive;
	}

	public void setPoolMaxActive(int poolMaxActive) {
		this.poolMaxActive = poolMaxActive;
	}

	public int getPoolMaxWait() {
		return poolMaxWait;
	}

	public void setPoolMaxWait(int poolMaxWait) {
		this.poolMaxWait = poolMaxWait;
	}

	public int getPoolMaxIdle() {
		return poolMaxIdle;
	}

	public void setPoolMaxIdle(int poolMaxIdle) {
		this.poolMaxIdle = poolMaxIdle;
	}

	public int getPoolMinIdle() {
		return poolMinIdle;
	}

	public void setPoolMinIdle(int poolMinIdle) {
		this.poolMinIdle = poolMinIdle;
	}

}
