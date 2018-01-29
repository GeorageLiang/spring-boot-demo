package com.spittr.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 服务启动类
 * @author chufei
 * 2018年1月17日
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.spittr")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}