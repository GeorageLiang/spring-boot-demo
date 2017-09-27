package com.spittr.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.spittr.interceptor.ControllerAccessInterceptor;

/**
 * web属性配置
 * @author chufei
 * @date 2017年9月24日
 */
@Configuration
public class WebMVCConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ControllerAccessInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
