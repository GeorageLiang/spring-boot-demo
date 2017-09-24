package com.spittr.utils.constant;

/**
 * 请求响应码常量类
 * 
 * @author chufei
 * @date 2017年4月10日
 */
public class CodeConstant {

	/**
	 * 响应成功
	 */
	public static final String SUCCESS = "0000";

	/**
	 * 请求异常
	 */
	public static final String EXCEPTION_REQUEST = "0006";
	
	/**
	 * 获取参数异常
	 */
	public static final String EXCEPTION_GET_PARAM = "0001";

	/**
	 * 参数超出范围
	 */
	public static final String PARAM_OUT_OF_RANGE = "0002";

	/**
	 * 业务处理异常
	 */
	public static final String EXCEPTION_SERVICE = "0003";

	/**
	 * 注册失败
	 */
	public static final String FAIL_REGISTER = "0004";

	/**
	 * 登录失败
	 */
	public static final String FAIL_LOGIN = "0005";

	/**
	 * 用户昵称为空
	 */
	public static final String ERR_NICKNAME_MISS = "1001";

	/**
	 * 登录密码为空
	 */
	public static final String ERR_PASSWORD_MISS = "1002";

	/**
	 * 手机号为空
	 */
	public static final String ERR_PHONE_MISS = "1003";

	/**
	 * 用户id为空
	 */
	public static final String ERR_USERID_MISS = "1004";

	/**
	 * 用户昵称已存在
	 */
	public static final String ERR_NICKNAME_EXIST = "1005";

	/**
	 * 手机号已存在
	 */
	public static final String ERR_PHONE_EXIST = "1006";

	/**
	 * 用户不存在
	 */
	public static final String ERR_USER_NOT_EXIST = "1007";

}
