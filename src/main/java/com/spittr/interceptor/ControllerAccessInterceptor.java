package com.spittr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.spittr.utils.RequestUtil;

/**
 * 控制器请求拦截器
 * 
 * @author chufei
 * @date 2017年9月24日
 */
public class ControllerAccessInterceptor implements HandlerInterceptor {

	private static Logger LOG = LoggerFactory.getLogger(ControllerAccessInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		LOG.info("remote address: {}, url: {}, params: {}.", arg0.getRemoteAddr(), arg0.getRequestURL(), RequestUtil.getRequestParams(arg0));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		return true;
	}

}
