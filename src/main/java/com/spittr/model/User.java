package com.spittr.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * spittr用户实体对象
 * 
 * @param userId
 *            用户ID
 * @param nickname
 *            用户昵称
 * @param password
 *            用户登录密码
 * @param gender
 *            性别，0-男，1-女，2-中
 * @param location
 *            所在地，目前用太阳系行星，默认地球
 * @param profile
 *            个人简介
 * @param phoneNum
 *            手机号
 * @param birthDay
 *            生日
 * @param registeredTime
 *            注册时间
 * @param age
 *            年龄
 * 
 * @author chufei
 * @date 2017年4月10日
 */
public class User implements Serializable {

	private static final long serialVersionUID = -7181648018888328880L;

	private long userId;

	private String nickname;

	@JsonIgnoreProperties
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
