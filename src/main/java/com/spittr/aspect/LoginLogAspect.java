package com.spittr.aspect;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spittr.constant.CodeConstant;
import com.spittr.controller.Response;
import com.spittr.mapper.master.LoginInfoMapper;
import com.spittr.model.User;
import com.spittr.utils.RequestUtil;

/**
 * 用户登录日志切面类
 * @author chufei
 * 2018年7月6日
 */
@Aspect
@Component
public class LoginLogAspect {

	private static final Logger LOGIN_LOG = LoggerFactory.getLogger(LoginLogAspect.class);
	
	@Autowired
	private LoginInfoMapper loginInfoMapper;
	
	@Pointcut("execution(* com.spittr.controller.IndexController.login(..))")
	private void loginLogPointCut() {}
	
	@Around("loginLogPointCut()")
	public Response<User> around(ProceedingJoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			String token = request.getParameter("token");
			String userAgent = request.getHeader("User-Agent");
			
			@SuppressWarnings("unchecked")
			Response<User> response = (Response<User>) joinPoint.proceed();
			if (response != null && response.isSuccess()) {
				User user = response.getData();
				long userId = user.getUserId();
				
				Date now = new Date();
				Map<String, Object> params = new HashMap<String, Object>(16);
				params.put("userId", userId);
				params.put("token", token == null ? "" : token);
				params.put("ip", RequestUtil.getRemoteIp(request));
				params.put("platform", RequestUtil.getPlatform(userAgent));
				params.put("loginTime", now);
				loginInfoMapper.loginLog(params);
				
				LOGIN_LOG.info("user login, userId: {}, token: {}, ip: {}, platform: {}, loginTime: {}",
						userId, token, RequestUtil.getRemoteIp(request), RequestUtil.getPlatform(userAgent), now);
			}
			return response;
		} catch (Throwable e) {
			e.printStackTrace();
			return new Response<>(CodeConstant.EXCEPTION_SERVICE, "");
		}
	}
	
	@AfterThrowing("loginLogPointCut()")
	public void afterThrowing() {
		HttpServletRequest request = (HttpServletRequest) RequestContextHolder.getRequestAttributes();
		String userId = request.getParameter("userId");
		String phoneNum = request.getParameter("phoneNum");
		String token = request.getParameter("token");
		String userAgent = request.getHeader("User-Agent");
		
		LOGIN_LOG.info("user login error, userId: {}, phoneNum: {}, token: {}, ip: {}, platform: {}, loginTime: {}",
				userId, phoneNum, token, RequestUtil.getRemoteIp(request), RequestUtil.getPlatform(userAgent), new Date());
	}
	
}
