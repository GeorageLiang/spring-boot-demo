package com.spittr.controller.wx;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spittr.constant.CodeConstant;
import com.spittr.controller.AbstractApiController;
import com.spittr.controller.Response;
import com.spittr.exception.SpittrException;
import com.spittr.service.WeChatUserService;
import com.spittr.utils.ParamUtil;

/**
 * 微信小程序控制器(未完成)
 * @author chufei
 * 2018年1月17日
 */
@RestController
@RequestMapping(value = "/wechat/u")
public class WeChatUserController extends AbstractApiController {

	private static Logger LOG = LoggerFactory.getLogger(WeChatUserController.class);

	@Autowired
	private WeChatUserService weChatUserService;

	@RequestMapping("/info/{id}")
	@ResponseBody
	public String getUserInfo(HttpServletRequest request, @PathVariable(value = "id") int id) {

		try {
			id = ParamUtil.toInt("id", id, true, -1, CodeConstant.ERR_USERID_MISS, 1, Integer.MAX_VALUE);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			return writeErrorJsonObject(request, e.getErrorCode(), "");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return writeErrorJsonObject(request, CodeConstant.EXCEPTION_GET_PARAM, "");
		}

		try {
			Response<Object> response = new Response<>();
			Map<String, Object> data = new HashMap<>(16);
			data.put("name", weChatUserService.getUserName(id));
			response.setData(data);
			response.setMessage(CodeConstant.SUCCESS);
			return writeJsonObject(request, response);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			return writeErrorJsonObject(request, e.getErrorCode(), "");
		}
	}

}
