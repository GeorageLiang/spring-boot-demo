package com.spittr.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spittr.interceptor.ControllerAccessInterceptor;

/**
 * web属性配置
 * @author chufei
 * @date 2017年9月24日
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ControllerAccessInterceptor()).addPathPatterns("/**");
	}
}
