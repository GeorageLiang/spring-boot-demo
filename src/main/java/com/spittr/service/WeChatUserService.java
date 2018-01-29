package com.spittr.service;

/**
 * 微信小程序相关业务service
 * @author chufei
 * 2018年1月17日
 */
public interface WeChatUserService {

	/**
	 * 根据用户id获取用户名
	 * @param id 用户id
	 * @return 用户名
	 */
	public String getUserName(int id);
	
}
