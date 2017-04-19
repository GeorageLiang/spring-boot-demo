package com.spittr.mapper;

import java.util.Map;

import com.spittr.model.User;

/**
 * 用户相关数据操作
 * 
 * @author chufei
 * @date 2017年4月10日
 */
public interface UserMapper {

	/**
	 * 用户注册
	 * 
	 * @param param
	 *            参数集合
	 */
	public void register(Map<String, Object> param);

	/**
	 * 根据用户id登录
	 * 
	 * @param param
	 *            参数集合
	 * @return 返回用户id
	 */
	public Long loginByUserId(Map<String, Object> param);

	/**
	 * 根据手机号登录
	 * 
	 * @param param
	 *            参数集合
	 * @return 返回用户id
	 */
	public Long loginByPhone(Map<String, Object> param);

	/**
	 * 根据昵称获取用户数
	 * 
	 * @param nickname
	 *            昵称
	 * @return 返回用户数量
	 */
	public int getUserCountByNickname(String nickname);

	/**
	 * 根据手机号获取用户数
	 * 
	 * @param phoneNum
	 *            手机号
	 * @return 返回用户数量
	 */
	public int getUserCountByPhoneNum(String phoneNum);

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userId
	 *            用户id
	 * @return 返回用户对象User
	 */
	public User getUserInfoById(long userId);

}
