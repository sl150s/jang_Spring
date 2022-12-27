package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.auto.MyCar;


@Configuration
public class JavaConfig {
	
	/*
	 *  @Bean 어노테이션과 함께 MyCar type을 리턴하는 메소드를 만들어 놓으면
	 *  MyCar type 객체가 spring이 관리하는 bean이 된다.
	 */
	
	@Bean
	public MyCar myCar() {
		MyCar c1 = new MyCar();
		return c1;
	}
	
}

