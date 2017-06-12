package com.spittr.utils.convert;

import java.util.Date;
import java.util.Map;

import com.google.gson.JsonObject;
import com.spittr.model.User;

/**
 * 用户对象模型转换
 * 
 * @author chufei
 * @date 2017年4月13日
 */
public class UserConvert {

	/**
	 * Map对象转化Uesr对象
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	public static User map2User(Map<String, String> map, User user) {
		if (user != null) {
			if (map.get("nickname") != null) {
				user.setNickname(map.get("nickname"));
			}
			if (map.get("password") != null) {
				user.setPassword(map.get("password"));
			}
			if (map.get("gender") != null) {
				user.setGender(Integer.parseInt(map.get("gender")));
			}
			if (map.get("age") != null) {
				user.setAge(Integer.parseInt(map.get("age")));
			}
			if (map.get("birthDay") != null) {
				user.setBirthDay(map.get("birthDay"));
			}
			if (map.get("location") != null) {
				user.setLocation(map.get("location"));
			}
			if (map.get("phoneNum") != null) {
				user.setPhoneNum(map.get("phoneNum"));
			}
			if (map.get("profile") != null) {
				user.setProfile(map.get("profile"));
			}
			if (map.get("registeredTime") != null) {
				user.setRegisteredTime(new Date(Long.parseLong(map.get("registeredTime"))));
			}
		}
		return user;
	}

	/**
	 * User对象转化Json对象
	 * 
	 * @param user
	 * @param jsonObject
	 * @return
	 */
	public static JsonObject user2Json(User user) {
		JsonObject jsonObject = new JsonObject();
		if (user != null) {
			if (user.getNickname() != null) {
				jsonObject.addProperty("nickname", user.getNickname());
			}
			jsonObject.addProperty("gender", user.getGender());
			jsonObject.addProperty("age", user.getAge());
			if (user.getBirthDay() != null) {
				jsonObject.addProperty("birthDay", user.getBirthDay());
			}
			if (user.getLocation() != null) {
				jsonObject.addProperty("location", user.getLocation());
			}
			if (user.getPhoneNum() != null) {
				jsonObject.addProperty("phoneNum", user.getPhoneNum());
			}
			if (user.getProfile() != null) {
				jsonObject.addProperty("profile", user.getProfile());
			}
			if (user.getRegisteredTime() != null) {
				jsonObject.addProperty("registeredTime", user.getRegisteredTime().getTime());
			}
		}
		return jsonObject;
	}

}
