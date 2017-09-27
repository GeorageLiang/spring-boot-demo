package com.spittr.controller.wx;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.spittr.controller.AbstractApiController;
import com.spittr.exception.SpittrException;
import com.spittr.service.WeChatUserService;
import com.spittr.utils.ParamUtil;
import com.spittr.utils.constant.CodeConstant;

@RestController
@RequestMapping(value = "/wechat/u")
public class WeChatUserController extends AbstractApiController {

	private static Logger LOG = Logger.getLogger(WeChatUserController.class);

	@Autowired
	private WeChatUserService weChatUserService;

	@RequestMapping("/info/{id}")
	@ResponseBody
	public String getUserInfo(HttpServletRequest request, @PathVariable(value = "id") int id) {
		JsonObject result = new JsonObject();

		try {
			id = ParamUtil.toInt("id", id, true, -1, CodeConstant.ERR_USERID_MISS, 1, Integer.MAX_VALUE);
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
			result.addProperty("name", weChatUserService.getUserName(id));
			result.addProperty("code", CodeConstant.SUCCESS);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			result.addProperty("code", e.getErrorCode());
		}

		return result.toString();
	}

}
