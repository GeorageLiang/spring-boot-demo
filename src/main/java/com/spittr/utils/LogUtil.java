package com.spittr.utils;

import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 日志工具类
 * 
 * @author chufei
 * @date 2017年7月6日
 */
public class LogUtil {

	/**
	 * 用户登录日志
	 */
	private static final Logger LOGIN_LOG = Logger.getLogger("SPITTR-LOGIN-LOG");

	/**
	 * 用户登录日志
	 * 
	 * @param userId
	 *            用户id
	 * @param token
	 *            登录token
	 * @param ip
	 *            登录ip
	 * @param platform
	 *            登录平台
	 * @param loginTime
	 *            登录时间
	 */
	public static void loginInfoLog(long userId, String token, String ip, int platform, Date loginTime) {
		LOGIN_LOG.info("user login, userId: " + userId + ", token: " + token + ", ip: " + ip + ", platform: " + platform
				+ ", loginTime: " + loginTime.toString());
	}

	/**
	 * 用户登录错误日志
	 * 
	 * @param userId
	 *            用户id
	 * @param token
	 *            登录token
	 * @param ip
	 *            登录ip
	 * @param platform
	 *            登录平台
	 * @param loginTime
	 *            登录时间
	 */
	public static void loginInfoErrorLog(long userId, String token, String ip, int platform, Date loginTime) {
		LOGIN_LOG.info("user login, error add table `login_info`, userId: " + userId + ", token: " + token + ", ip: "
				+ ip + ", platform: " + platform + ", loginTime: " + loginTime.toString());
	}

}
