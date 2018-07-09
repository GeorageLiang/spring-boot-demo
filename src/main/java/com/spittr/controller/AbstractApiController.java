package com.spittr.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spittr.constant.CodeConstant;
import com.spittr.utils.RequestUtil;

/**
 * api接口通用父类
 * 
 * @author chufei
 * @date 2017年6月21日
 */
public abstract class AbstractApiController {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractApiController.class);
	
	protected static final ObjectMapper JSON_MAPPER;
	
	static {
		JSON_MAPPER = new ObjectMapper();
		// 配置mapper序列化忽略null值
		JSON_MAPPER.setSerializationInclusion(Include.NON_NULL);
		// 禁用未知属性打断反序列化功能
		JSON_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
	}
	
	@ExceptionHandler
	protected String exceptionHandler(HttpServletRequest request) {
		LOG.error("access api error, url: {}, ip: {}, params: {}.", request.getRequestURL(), RequestUtil.getRemoteIp(request), RequestUtil.getRequestParams(request));
		return writeErrorJsonObject(request, CodeConstant.SERVER_ERROR, "阿哦~系统出错了~请稍候重试~");
	}
	
	protected String writeErrorJsonObject(HttpServletRequest request, String code, String message) {
		Response<Object> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		return writeJsonObject(request, response);
	}
	
	protected String writeJsonObject(HttpServletRequest request, Response<Object> object) {
		try {
			return JSON_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			LOG.error("write json response throw JsonProcessingException", e);
			Response<Object> response = new Response<>();
			response.setCode(CodeConstant.SERVER_ERROR);
			response.setMessage("阿哦~系统出错了~请稍候重试~");
			try {
				return JSON_MAPPER.writeValueAsString(response);
			} catch (JsonProcessingException e1) {
				throw new IllegalStateException("Check the jackson ObjectMapper configuration.", e1);
			}
		}
	}
	
}
