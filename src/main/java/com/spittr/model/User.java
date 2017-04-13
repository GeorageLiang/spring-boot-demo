package com.spittr.model;

import java.io.Serializable;
import java.util.Date;

/**
 * spittr用户实体对象
 * 
 * @property userId 用户ID
 * @property nickname 用户昵称
 * @property password 用户登录密码
 * @property gender 性别，0-男，1-女，2-中
 * @property location 所在地，目前用太阳系行星，默认地球
 * @property profile 个人简介
 * @property phoneNum 手机号
 * @property birthDay 生日
 * @property registeredTime 注册时间
 * @property age 年龄
 * 
 * @author chufei
 * @date 2017年4月10日
 */
public class User implements Serializable {

	private static final long serialVersionUID = -7181648018888328880L;

	private long userId;

	private String nickname;

	private String password;

	private int gender;

	private String location;

	private String profile;

	private String phoneNum;

	private String birthDay;

	private Date registeredTime;

	private int age;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public Date getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
