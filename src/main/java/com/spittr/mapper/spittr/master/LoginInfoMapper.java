package com.spittr.mapper.spittr.master;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户登录信息数据操作
 * 
 * @author chufei
 * @date 2017年7月6日
 */
@Mapper
public interface LoginInfoMapper {

	/**
	 * 插入用户登录记录
	 * 
	 * @param params
	 */
	@Insert(value = "insert into `login_info` (`user_id`, `token`, `ip`, `platform`, `login_time`) "
			+ "values (#{userId}, #{token}, #{ip}, #{platform}, #{loginTime})")
	public void loginLog(Map<String, Object> params);

}
