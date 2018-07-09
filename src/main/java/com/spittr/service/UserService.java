package com.spittr.service;

import com.spittr.model.User;

/**
 * 用户相关业务操作
 * 
 * @author chufei
 * @date 2017年4月10日
 */
public interface UserService {

	/**
	 * 用户注册
	 * 
	 * @param nickname
	 *            昵称
	 * @param password
	 *            加密前密码
	 * @param gender
	 *            性别
	 * @param location
	 *            所在地
	 * @param profile
	 *            个人简介
	 * @param phoneNum
	 *            手机号
	 * @param birthDay
	 *            出生日期
	 * @param registeredPlatform
	 *            注册平台
	 * @return 返回用户id
	 */
	public long register(String nickname, String password, int gender, String location, String profile, String phoneNum,
			String birthDay, int registeredPlatform);

	/**
	 * 用户登录
	 * 
	 * @param userId
	 *            用户id，选填
	 * @param phoneNum
	 *            手机号，选填
	 * @param password
	 *            登录密码
	 * @return 返回用户对象
	 */
	public User login(long userId, String phoneNum, String password);

	/**
	 * 判断用户名是否占用
	 * 
	 * @param nickname
	 *            昵称
	 * @return true 已占用， false未占用
	 */
	public boolean isExistNickname(String nickname);

	/**
	 * 判断手机号是否占用
	 * 
	 * @param phoneNum
	 *            手机号
	 * @return true 已占用， false未占用
	 */
	public boolean isExistPhone(String phoneNum);

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userId
	 *            用户id
	 * @return 返回用户对象User
	 */
	public User getUserInfoById(long userId);

}
