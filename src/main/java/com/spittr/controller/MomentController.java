package com.spittr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spittr.constant.CodeConstant;
import com.spittr.exception.SpittrException;
import com.spittr.model.Moment;
import com.spittr.service.MomentService;
import com.spittr.utils.ParamUtil;

/**
 * 动态相关控制器
 * @author chufei
 * 2018年1月17日
 */
@RestController
@RequestMapping(value = "/moment")
public class MomentController extends AbstractApiController {

	private static final Logger LOG = LoggerFactory.getLogger(MomentController.class);

	@Autowired
	private MomentService momentService;

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	@ResponseBody
	public String publishMoment(HttpServletRequest request, @RequestParam("token") String token,
			@RequestParam(value = "userId", required = false, defaultValue = "0") long userId,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "isDisplay", required = false, defaultValue = "0") int isDisplay,
			@RequestParam(value = "addType", required = false, defaultValue = "0") int addType,
			@RequestParam(value = "addIndexs", required = false) int[] addIndexs,
			@RequestParam(value = "addUrls", required = false) String[] addUrls) {
		// TODO 校验token

		try {
			userId = ParamUtil.toLong("userId", userId, true, 0, CodeConstant.ERR_USERID_MISS, 1, Long.MAX_VALUE);
			content = ParamUtil.toString("content", content, true, "", "2001", 1, 1000);
			isDisplay = ParamUtil.toInt("isDisplay", isDisplay, false, 0, null, 0, 1);
			addType = ParamUtil.toInt("addType", addType, false, 0, null, 0, 10);
			addIndexs = ParamUtil.toIntArray("addIndexs", addIndexs, false, new int[0], null, 0, 100);
			addUrls = ParamUtil.toStringArray("addUrls", addUrls, false, new String[0], null, 0, 100);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			return writeErrorJsonObject(request, e.getErrorCode(), "");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return writeErrorJsonObject(request, CodeConstant.EXCEPTION_GET_PARAM, "");
		}

		// 如果需要上传附加信息
		if (addType > 0) {
			int indexLength = addIndexs.length;
			if (indexLength < 1) {
				// 附加信息数量不能少于1
				return writeErrorJsonObject(request, "2002", "");
			}
			if (indexLength != addUrls.length) {
				// 附加信息数量无法对应
				return writeErrorJsonObject(request, "2003", "");
			}
		}

		try {
			long momentId = momentService.addMoment(userId, content, isDisplay, addType, addIndexs, addUrls);
			if (momentId > 0) {
				Response<Object> response = new Response<>();
				response.setData(momentService.getMomentByMomentId(momentId));
				response.setCode(CodeConstant.SUCCESS);
				return writeJsonObject(request, response);
			} else {
				// 动态发布失败
				return writeErrorJsonObject(request, "2004", "发布失败");
			}
		} catch (SpittrException e) {
			LOG.error(e.getMessage(), e);
			return writeErrorJsonObject(request, e.getErrorCode(), "");
		}
	}

	@RequestMapping(value = "/{momentId}", method = RequestMethod.GET)
	@ResponseBody
	public String getMoment(HttpServletRequest request, @PathVariable(value = "momentId") long momentId) {

		try {
			momentId = ParamUtil.toLong("momentId", momentId, true, 0L, "2004", 1, Long.MAX_VALUE);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			return writeErrorJsonObject(request, e.getErrorCode(), "");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return writeErrorJsonObject(request, CodeConstant.EXCEPTION_GET_PARAM, "");
		}

		try {
			Moment moment = momentService.getMomentByMomentId(momentId);
			Response<Object> response = new Response<>();
			response.setData(moment);
			response.setCode(CodeConstant.SUCCESS);
			return writeJsonObject(request, response);
		} catch (SpittrException e) {
			LOG.error(e.getMessage(), e);
			return writeErrorJsonObject(request, e.getErrorCode(), "");
		}
	}

	@RequestMapping(value = "/display", method = RequestMethod.POST)
	@ResponseBody
	public String displayMoment(HttpServletRequest request, @RequestParam("token") String token,
			@RequestParam(value = "momentId", required = false) long momentId) {
		// TODO 校验用户token

		try {
			momentId = ParamUtil.toLong("momentId", momentId, true, 0L, "2004", 1, Long.MAX_VALUE);
		} catch (SpittrException e) {
			LOG.error(e.getMessage());
			return writeErrorJsonObject(request, e.getErrorCode(), "");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return writeErrorJsonObject(request, CodeConstant.EXCEPTION_GET_PARAM, "");
		}

		try {
			int isDisplay = momentService.displayMoment(momentId);
			if (isDisplay < 0) {
				return writeErrorJsonObject(request, "2006", "设置失败");
			} else {
				Response<Object> response = new Response<>();
				Map<String, Object> data = new HashMap<>(16);
				data.put("isDisplay", isDisplay);
				response.setData(data);
				response.setMessage(CodeConstant.SUCCESS);
				return writeJsonObject(request, response);
			}
		} catch (SpittrException e) {
			LOG.error(e.getMessage(), e);
			return writeErrorJsonObject(request, e.getErrorCode(), "");
		}
	}

}
