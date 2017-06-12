package com.spittr.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.spittr.model.User;
import com.spittr.service.UserService;
import com.spittr.utils.ParamUtil;
import com.spittr.utils.SpittrException;
import com.spittr.utils.constant.CodeConstant;
import com.spittr.utils.convert.UserConvert;

/**
 * 用户相关控制器
 * 
 * @author chufei
 * @date 2017年4月12日
 */
@RestController
@RequestMapping(value = "/u")
public class UserController {

	private static Logger LOG = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/info/{userId}")
	@ResponseBody
	public String getUserInfo(@PathVariable(value = "userId") long userId) {
		JsonObject result = new JsonObject();

		try {
			userId = ParamUtil.toLong("userId", userId, true, -1, CodeConstant.ERR_USERID_MISS, 1, Long.MAX_VALUE);
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
			User user = userService.getUserInfoById(userId);
			result.add("userInfo", UserConvert.user2Json(user));
			result.addProperty("code", CodeConstant.SUCCESS);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			result.addProperty("code", e.getErrorCode());
			return result.toString();
		}

		return result.toString();
	}

}
