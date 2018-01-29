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

import com.spittr.constant.CodeConstant;
import com.spittr.constant.UserConstant;
import com.spittr.exception.SpittrException;
import com.spittr.model.User;
import com.spittr.service.UserService;
import com.spittr.utils.ParamUtil;

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
		return "Spittr Home!";
	}

	/**
	 * 用户注册
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Response<User> register(HttpServletRequest request, @RequestHeader(value = "User-Agent") String userAgent,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "gender", required = false, defaultValue = "2") int gender,
			@RequestParam(value = "location", required = false) String location,
			@RequestParam(value = "profile", required = false) String profile,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "birthDay", required = false) String birthDay) {
		Response<User> response = new Response<>();

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
			response.setCode(e.getErrorCode());
			return response;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			response.setCode(CodeConstant.EXCEPTION_GET_PARAM);
			return response;
		}

		try {
			// 判断用户名是否已占用
			if (userService.isExistNickname(nickname)) {
				response.setCode(CodeConstant.ERR_NICKNAME_EXIST);
				return response;
			}

			// 判断手机号是否已占用
			if (userService.isExistPhone(phoneNum)) {
				response.setCode(CodeConstant.ERR_PHONE_EXIST);
				return response;
			}

			int platform = getPlatform(userAgent);
			// 注册用户信息
			long userId = userService.register(nickname, password, gender, location, profile, phoneNum, birthDay,
					platform);
			if (userId < 1) {
				response.setCode(CodeConstant.FAIL_REGISTER);
				response.setMessage("registered failed!");
				return response;
			}

			// 用户登录
			response = login(request, userAgent, userId, phoneNum, password);

			return response;
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			response.setCode(e.getErrorCode());
			return response;
		}
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public Response<User> login(HttpServletRequest request, @RequestHeader(value = "User-Agent") String userAgent,
			@RequestParam(value = "userId", required = false, defaultValue = "0") long userId,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "password") String password) {
		Response<User> response = new Response<>();

		try {
			userId = ParamUtil.toLong("userId", userId == 0 ? null : userId, false, 0, null, 1, Long.MAX_VALUE);
			phoneNum = ParamUtil.toString("phoneNum", phoneNum, false, "", null, 1, 20);
			password = ParamUtil.toString("password", password, true, "", CodeConstant.ERR_PASSWORD_MISS, 1, 255);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			response.setCode(e.getErrorCode());
			return response;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			response.setCode(CodeConstant.EXCEPTION_GET_PARAM);
			return response;
		}

		try {
			User user = userService.login(userId, phoneNum, password);
			if (user == null) {
				response.setCode(CodeConstant.ERR_USER_NOT_EXIST);
				return response;
			}

			// TODO 获取用户登录token
			String token = "";

			String ip = getRemoteIp(request);
			int platform = getPlatform(userAgent);
			userService.loginLog(user.getUserId(), token, ip, platform);

			response.setData(user);
			response.setCode(CodeConstant.SUCCESS);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			response.setCode(e.getErrorCode());
		}

		return response;
	}

	/**
	 * 用户退出登录
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request,
			@RequestParam(value = "userId", required = false, defaultValue = "0") long userId) {

		return "";
	}
}
