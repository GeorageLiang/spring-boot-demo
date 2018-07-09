package com.spittr.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spittr.constant.CodeConstant;
import com.spittr.exception.SpittrException;
import com.spittr.model.User;
import com.spittr.service.UserService;
import com.spittr.utils.ParamUtil;

/**
 * 用户相关控制器
 * 
 * @author chufei
 * @date 2017年4月12日
 */
@RestController
@RequestMapping(value = "/u")
public class UserController extends AbstractApiController {

	private static Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/info/{userId}")
	@ResponseBody
	public String getUserInfo(HttpServletRequest request, @PathVariable(value = "userId") long userId) {

		try {
			userId = ParamUtil.toLong("userId", userId, true, -1, CodeConstant.ERR_USERID_MISS, 1, Long.MAX_VALUE);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			return writeErrorJsonObject(request, e.getErrorCode(), "");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return writeErrorJsonObject(request, CodeConstant.EXCEPTION_GET_PARAM, "");
		}

		try {
			User user = userService.getUserInfoById(userId);
			if (user != null) {
				Response<Object> response = new Response<>();
				response.setData(user);
				response.setCode(CodeConstant.SUCCESS);
				return writeJsonObject(request, response);
			} else {
				return writeErrorJsonObject(request, CodeConstant.ERR_USER_NOT_EXIST, "");
			}
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			return writeErrorJsonObject(request, e.getErrorCode(), "");
		}

	}

}
