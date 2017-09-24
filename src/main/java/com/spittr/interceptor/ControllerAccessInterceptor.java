package com.spittr.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 控制器请求拦截器
 * 
 * @author chufei
 * @date 2017年9月24日
 */
public class ControllerAccessInterceptor implements HandlerInterceptor {

	private static Logger LOG = Logger.getLogger("SPITTR-ACCESS-LOG");

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		LOG.info("remote address: " + arg0.getRemoteAddr() + ", url: " + arg0.getRequestURL() + ", params: "
				+ getRequestParams(arg0));
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

	/**
	 * 获取请求参数
	 * 
	 * @param request
	 *            请求
	 * @return
	 */
	protected String getRequestParams(HttpServletRequest request) {
		Map<String, String[]> paramMap = request.getParameterMap();
		StringBuffer stringBuffer = new StringBuffer();
		for (String key : paramMap.keySet()) {
			stringBuffer.append("param[" + key + "=" + paramMap.get(key) + "],");
		}
		return stringBuffer.toString();
	}
}
