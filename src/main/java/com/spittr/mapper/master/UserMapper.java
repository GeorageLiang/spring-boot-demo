package com.spittr.mapper.master;

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
	 * @param params 入参集合，具体如下
	 * <p>nickname: 昵称</p>
	 * <p>password: 登录密码</p>
	 * <p>gender: 性别，0-男，1-女，2-中</p>
	 * <p>location: 所在地</p>
	 * <p>profile: 个人简介</p>
	 * <p>birthDay: 出生日期</p>
	 * <p>registeredTime: 注册时间</p>
	 * <p>phoneNum: 手机号</p>
	 * <p>age: 年龄</p>
	 * <p>registeredPlatform: 用户注册平台</p>
	 */
	@Insert(value = "insert into `user_info` (`nickname`, `password`, `gender`, `location`, `profile`, `birth_day`, `registered_time`, `phone_num`, `age`, `registered_platform`)"
			+ "	values (#{nickname}, #{password}, #{gender}, #{location}, #{profile}, #{birthDay}, #{registeredTime}, #{phoneNum}, #{age}, #{registeredPlatform})")
	@SelectKey(before = false, keyProperty = "userId", resultType = Long.class, statement = {
			"SELECT LAST_INSERT_ID()" })
	public void register(Map<String, Object> params);

	/**
	 * 根据用户id登录
	 * 
	 * @param params 入参集合，具体如下
	 * <p>userId: 用户id</p>
	 * <p>password: 登录密码</p>
	 * @return 返回用户id
	 */
	@Select(value = "select `user_id` from `user_info` where `user_id` = #{userId} and `password` = #{password}")
	public Long loginByUserId(Map<String, Object> params);

	/**
	 * 根据手机号登录
	 * 
	 * @param params 入参集合，具体如下
	 * <p>phoneNum: 手机号</p>
	 * <p>password: 登录密码</p>
	 * @return 返回用户id
	 */
	@Select(value = "select `user_id` from `user_info` where `phone_num` = #{phoneNum} and `password` = #{password}")
	public Long loginByPhone(Map<String, Object> params);

	/**
	 * 判断昵称是否已存在
	 * 
	 * @param nickname
	 *            昵称
	 * @return 返回1表示已存在，null表示不存在
	 */
	@Select(value = "select 1 from `user_info` where `nickname` = #{nickname}")
	public Integer getUserCountByNickname(String nickname);

	/**
	 * 判断手机号是否已存在
	 * 
	 * @param phoneNum
	 *            手机号
	 * @return 返回1表示已存在，null表示不存在
	 */
	@Select(value = "select 1 from `user_info` where `phone_num` = #{phoneNum}")
	public Integer getUserCountByPhoneNum(String phoneNum);

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userId
	 *            用户id
	 * @return 返回用户对象User
	 */
	@Select(value = "select" + " `user_id` as userId," + "	`nickname` as nickname," + "	`password` as password,"
			+ " `gender` as gender," + "	`location` as location," + "	`profile` as profile,"
			+ "	`birth_day` as birthDay," + "	`registered_time` as registeredTime," + "	`phone_num` as phoneNum,"
			+ "	`age` as age" + "	from `user_info` where user_id = #{userId}")
	public User getUserInfoById(long userId);

}
