package com.spittr.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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

	private static Logger LOG = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/info/{userId}")
	@ResponseBody
	public Response<User> getUserInfo(HttpServletRequest request, @PathVariable(value = "userId") long userId) {
		Response<User> response = new Response<>();

		try {
			userId = ParamUtil.toLong("userId", userId, true, -1, CodeConstant.ERR_USERID_MISS, 1, Long.MAX_VALUE);
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
			User user = userService.getUserInfoById(userId);
			if (user != null) {
				response.setData(user);
				response.setCode(CodeConstant.SUCCESS);
			} else {
				response.setCode(CodeConstant.ERR_USER_NOT_EXIST);
			}
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			response.setCode(e.getErrorCode());
		}

		return response;
	}

}
