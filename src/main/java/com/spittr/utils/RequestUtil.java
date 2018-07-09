package com.spittr.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.spittr.constant.CommonConstant;
import com.spittr.constant.PlatformConstant;
import com.theft.code.utils.string.StringUtil;

/**
 * HttpServletRequest工具类
 * @author chufei
 * 2018年7月6日
 */
public class RequestUtil {

	/**
	 * 获取客户端请求真实ip，不适用于ipv6
	 * 
	 * @param request
	 *            请求
	 * @return ip地址
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Real-IP");
		if (ipAddress == null || ipAddress.length() == 0 || CommonConstant.NULL_VALUE.equalsIgnoreCase(ipAddress)
				|| CommonConstant.UNKNOWN_VALUE.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Cluster-Client-Ip");
		}
		if (ipAddress == null || ipAddress.length() == 0 || CommonConstant.NULL_VALUE.equalsIgnoreCase(ipAddress)
				|| CommonConstant.UNKNOWN_VALUE.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Forwarded-For");
		}
		if (ipAddress == null || ipAddress.length() == 0 || CommonConstant.NULL_VALUE.equalsIgnoreCase(ipAddress)
				|| CommonConstant.UNKNOWN_VALUE.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || CommonConstant.NULL_VALUE.equalsIgnoreCase(ipAddress)
				|| CommonConstant.UNKNOWN_VALUE.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || CommonConstant.NULL_VALUE.equalsIgnoreCase(ipAddress)
				|| CommonConstant.UNKNOWN_VALUE.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}

		// ipv6格式
		if (ipAddress != null && !ipAddress.isEmpty()) {
			int index = ipAddress.indexOf(":");
			if (index > 0) {
				ipAddress = ipAddress.substring(0, index);
			}
		}

		return ipAddress;
	}
	
	/**
	 * 获取request请求参数
	 * 
	 * @param request
	 *            请求
	 * @return 请求参数明细
	 */
	public static String getRequestParams(HttpServletRequest request) {
		Map<String, String[]> paramMap = request.getParameterMap();
		StringBuffer stringBuffer = new StringBuffer();
		for (String key : paramMap.keySet()) {
			stringBuffer.append("param[" + key + "=" + paramMap.get(key) + "],");
		}
		return stringBuffer.toString();
	}

	/**
	 * 根据请求头信息获取来源平台
	 * 
	 * @param userAgent
	 *            请求头信息
	 * @return 请求来源平台
	 */
	public static int getPlatform(String userAgent) {
		if (StringUtil.strIsNull(userAgent)) {
			return PlatformConstant.PLATFORM_UNKNOWN;
		}
		if (userAgent.contains(CommonConstant.USER_AGENT_IPHONE) || userAgent.contains(CommonConstant.USER_AGENT_IPOD) || userAgent.contains(CommonConstant.USER_AGENT_IPAD)) {
			return PlatformConstant.PLATFORM_IOS;
		} else if (userAgent.contains(CommonConstant.USER_AGENT_ANDROID)) {
			return PlatformConstant.PLATFORM_ANDROID;
		} else if (userAgent.contains(CommonConstant.USER_AGENT_WINDOWS_PHONE)) {
			return PlatformConstant.PLATFORM_WINPHONE;
		} else {
			return PlatformConstant.PLATFORM_UNKNOWN;
		}
	}
}
