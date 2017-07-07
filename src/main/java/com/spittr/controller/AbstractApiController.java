package com.spittr.controller;

import javax.servlet.http.HttpServletRequest;

import com.spittr.utils.constant.PlatformConstant;
import com.theft.code.utils.string.StringUtil;

/**
 * api接口通用父类
 * 
 * @author chufei
 * @date 2017年6月21日
 */
public abstract class AbstractApiController {

	/**
	 * 获取客户端请求真实ip，不适用于ipv6
	 * 
	 * @param request
	 *            请求
	 * @return ip地址
	 */
	protected String getRemoteIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Real-IP");
		if (ipAddress == null || ipAddress.length() == 0 || "null".equalsIgnoreCase(ipAddress)
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Cluster-Client-Ip");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "null".equalsIgnoreCase(ipAddress)
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Forwarded-For");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "null".equalsIgnoreCase(ipAddress)
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "null".equalsIgnoreCase(ipAddress)
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "null".equalsIgnoreCase(ipAddress)
				|| "unknown".equalsIgnoreCase(ipAddress)) {
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
	 * 根据请求头信息获取来源平台
	 * 
	 * @param userAgent
	 *            请求头信息
	 * @return 请求来源平台
	 */
	protected int getPlatform(String userAgent) {
		if (StringUtil.strIsNull(userAgent)) {
			return PlatformConstant.PLATFORM_UNKNOWN;
		}
		if (userAgent.contains("iPhone") || userAgent.contains("iPod") || userAgent.contains("iPad")) {
			return PlatformConstant.PLATFORM_IOS;
		} else if (userAgent.contains("Android")) {
			return PlatformConstant.PLATFORM_ANDROID;
		} else if (userAgent.contains("Windows Phone")) {
			return PlatformConstant.PLATFORM_WINPHONE;
		} else {
			return PlatformConstant.PLATFORM_UNKNOWN;
		}
	}

}
