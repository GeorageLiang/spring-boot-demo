package com.spittr.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.spittr.model.User;
import com.spittr.service.UserService;
import com.spittr.utils.ParamUtil;
import com.spittr.utils.SpittrException;
import com.spittr.utils.constant.CodeConstant;
import com.spittr.utils.constant.UserConstant;
import com.spittr.utils.convert.UserConvert;

/**
 * 首页核心控制器 包括 用户注册 用户登录 用户登出
 * 
 * @author chufei
 * @date 2017年4月10日
 */
@RestController
@RequestMapping(value = "/")
public class IndexController extends AbstractApiController {

	private static Logger LOG = Logger.getLogger(IndexController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	@ResponseBody
	public String guide() {
		return "welcome to Spittr!";
	}

	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		JsonObject result = new JsonObject();
		return result.toString();
	}

	/**
	 * 用户注册
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(HttpServletRequest request, @RequestHeader(value = "User-Agent") String userAgent,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "gender", required = false, defaultValue = "2") int gender,
			@RequestParam(value = "location", required = false) String location,
			@RequestParam(value = "profile", required = false) String profile,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "birthDay", required = false) String birthDay) {
		JsonObject result = new JsonObject();

		try {
			nickname = ParamUtil.toString("nickname", nickname, true, "", CodeConstant.ERR_NICKNAME_MISS, 1, 100);
			password = ParamUtil.toString("password", password, true, "", CodeConstant.ERR_PASSWORD_MISS, 1, 255);
			gender = ParamUtil.toInt("gender", gender, false, UserConstant.DEFAULT_USER_GENDER, null, 0, 2);
			location = ParamUtil.toString("location", location, false, UserConstant.DEFAULT_USER_LOCATION, null, 1, 20);
			profile = ParamUtil.toString("profile", profile, false, UserConstant.DEFAULT_USER_PROFILE, null, 1, 255);
			phoneNum = ParamUtil.toString("phoneNum", phoneNum, true, "", CodeConstant.ERR_PHONE_MISS, 1, 20);
			birthDay = ParamUtil.toString("birthDay", birthDay, false, UserConstant.DEFAULT_USER_BIRTHDAY, null, 1, 10);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			result.addProperty("code", e.getErrorCode());
			return result.toString();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.addProperty("code", CodeConstant.EXCEPTION_GET_PARAM);
			return result.toString();
		}

		try {
			// 判断用户名是否已占用
			if (userService.isExistNickname(nickname)) {
				result.addProperty("code", CodeConstant.ERR_NICKNAME_EXIST);
				return result.toString();
			}

			// 判断手机号是否已占用
			if (userService.isExistPhone(phoneNum)) {
				result.addProperty("code", CodeConstant.ERR_PHONE_EXIST);
				return result.toString();
			}

			int platform = getPlatform(userAgent);
			// 注册用户信息
			long userId = userService.register(nickname, password, gender, location, profile, phoneNum, birthDay,
					platform);
			if (userId < 1) {
				result.addProperty("code", CodeConstant.FAIL_REGISTER);
				result.addProperty("message", "registered failed!");
				return result.toString();
			}
			result.addProperty("userId", userId);

			// 用户登录
			String loginResult = login(request, userAgent, userId, phoneNum, password);

			return loginResult;
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			result.addProperty("code", e.getErrorCode());
			return result.toString();
		}
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, @RequestHeader(value = "User-Agent") String userAgent,
			@RequestParam(value = "userId", required = false, defaultValue = "0") long userId,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "password") String password) {
		JsonObject result = new JsonObject();

		try {
			userId = ParamUtil.toLong("userId", userId == 0 ? null : userId, false, 0, null, 1, Long.MAX_VALUE);
			phoneNum = ParamUtil.toString("phoneNum", phoneNum, false, "", null, 1, 20);
			password = ParamUtil.toString("password", password, true, "", CodeConstant.ERR_PASSWORD_MISS, 1, 255);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			result.addProperty("code", e.getErrorCode());
			return result.toString();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.addProperty("code", CodeConstant.EXCEPTION_GET_PARAM);
			return result.toString();
		}

		try {
			User user = userService.login(userId, phoneNum, password);
			if (user == null) {
				result.addProperty("code", CodeConstant.ERR_USER_NOT_EXIST);
				return result.toString();
			}

			// TODO 获取用户登录token
			String token = "";

			String ip = getRemoteIp(request);
			int platform = getPlatform(userAgent);
			userService.loginLog(user.getUserId(), token, ip, platform);

			result.addProperty("userId", user.getUserId());
			result.add("userInfo", UserConvert.user2Json(user));
			result.addProperty("code", CodeConstant.SUCCESS);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			result.addProperty("code", e.getErrorCode());
		}

		return result.toString();
	}

	/**
	 * 用户退出登录
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String login(HttpServletRequest request,
			@RequestParam(value = "userId", required = false, defaultValue = "0") long userId) {

		return "";
	}
}
