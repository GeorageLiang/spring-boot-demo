package com.spittr.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.spittr.model.User;

/**
 * 用户相关数据操作
 * 
 * @author chufei
 * @date 2017年4月10日
 */
@Mapper
public interface UserMapper {

	/**
	 * 用户注册
	 * 
	 * @param param
	 *            参数集合
	 */
	@Insert(value = "insert into `user_info` (`nickname`, `password`, `gender`, `location`, `profile`, `birth_day`, `registered_time`, `phone_num`, `age`)" 
			+ "	values (#{nickname}, #{password}, #{gender}, #{location}, #{profile}, #{birthDay}, #{registeredTime}, #{phoneNum}, #{age})")
	@SelectKey(before = false, keyProperty = "userId", resultType = Long.class, statement = { "SELECT LAST_INSERT_ID()" })
	public void register(Map<String, Object> param);

	/**
	 * 根据用户id登录
	 * 
	 * @param param
	 *            参数集合
	 * @return 返回用户id
	 */
	@Select(value = "select `user_id` from `user_info` where `user_id` = #{userId} and `password` = #{password}")
	public Long loginByUserId(Map<String, Object> param);

	/**
	 * 根据手机号登录
	 * 
	 * @param param
	 *            参数集合
	 * @return 返回用户id
	 */
	@Select(value = "select `user_id` from `user_info` where `phone_num` = #{phoneNum} and `password` = #{password}")
	public Long loginByPhone(Map<String, Object> param);

	/**
	 * 根据昵称获取用户数
	 * 
	 * @param nickname
	 *            昵称
	 * @return 返回用户数量
	 */
	@Select(value = "select count(1) from `user_info` where `nickname` = #{nickname}")
	public int getUserCountByNickname(String nickname);

	/**
	 * 根据手机号获取用户数
	 * 
	 * @param phoneNum
	 *            手机号
	 * @return 返回用户数量
	 */
	@Select(value = "select count(1) from `user_info` where `phone_num` = #{phoneNum}")
	public int getUserCountByPhoneNum(String phoneNum);

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userId
	 *            用户id
	 * @return 返回用户对象User
	 */
	@Select(value = "select"
			+ " `user_id` as userId," 
			+ "	`nickname` as nickname,"
			+ "	`password` as password," 
			+ " `gender` as gender," 
			+ "	`location` as location,"
			+ "	`profile` as profile," 
			+ "	`birth_day` as birthDay," 
			+ "	`registered_time` as registeredTime," 
			+ "	`phone_num` as phoneNum," 
			+ "	`age` as age"
			+ "	from `user_info` where user_id = #{userId}")
	public User getUserInfoById(long userId);

}
