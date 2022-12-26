package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 어떤 객체를 spring이 생성해서 관리할 지 설정 (예전 spring에서는 xml 문서에서 bean 설정을 했음, springboot 에서는 bean설정을 class로 한다) 
 */
@Configuration
public class JavaConfig {

	// 이 메소드에서 리턴되는 객체를 spring이 관리하는 bean이 되도록한다.
	@Bean
	public Car myCar() { //myCar : bean의 이름 역할도 된다
		System.out.println("myCar 메소드 호출됨");
		Car c1 = new Car();
		return c1;
	}
}
