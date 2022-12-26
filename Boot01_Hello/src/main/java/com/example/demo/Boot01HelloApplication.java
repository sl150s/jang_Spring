package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Boot01HelloApplication {

	public static void main(String[] args) {
		//SpringApplication 클래스의 static 메소드 run()을 호출하면 ApplicationContext 객체가 리턴된다. 
		ApplicationContext ctx = SpringApplication.run(Boot01HelloApplication.class, args);
		
		//spring이 관리하는 bean 중에서 Car type을 찾아서 받아오기
		Car car1 = ctx.getBean(Car.class);
		car1.drive();
		
		
	}

}
