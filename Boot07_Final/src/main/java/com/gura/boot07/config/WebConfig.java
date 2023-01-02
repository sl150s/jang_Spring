package com.gura.boot07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gura.boot07.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	//로그인 인터셉터 주입 받기 
	@Autowired LoginInterceptor loginInterceptor;
	
	//인터셉터 동작하도록 등록하기
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/users/*", "/gallery/*", "/cafe/*", "/file/*", "/shop/*")
		.excludePathPatterns("/users/signup_form", "/users/signup", "/users/loginform", "/users/login",
				"/gallery/upload_form","/gallery/upload","/gallery/list"," /gallery/detail",
				"/cafe/list","/cafe/detail","/cafe/ajax_comment_list", 
				"/file/list", "file/download/", 
				"/users/profile/*");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
