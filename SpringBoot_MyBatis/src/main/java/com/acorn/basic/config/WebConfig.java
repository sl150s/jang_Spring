package com.acorn.basic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//설정과 관련된 java 파일에는 @Configuration 이라는 어노테이션을 붙여준다. 
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	//webapp 폴더에 resources 폴더를 만들고 해당 폴더에 있는 컨텐츠는 spring이 관여하지 않고 바로 응답되도록 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//메소드의 인자로 전달되는 객체를 활용해서 요청경로와 서비스경로를 설정한다.
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
