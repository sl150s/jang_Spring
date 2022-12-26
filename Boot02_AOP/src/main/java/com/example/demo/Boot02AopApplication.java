package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.util.Messenger;
import com.example.demo.util.WritingUtil;

/*
 * @SpringBootApplication 어노테이션이 붙어있는 main 메소드가 존재하는 이 패키지를 포함해서
 * 
 * 하위의모든 패키지에 component scan이 자동으로 일어난다. 
 * 
 */

@SpringBootApplication
public class Boot02AopApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Boot02AopApplication.class, args);

		// 편지를 쓰고 싶다면
		// new Writingutil().write1();
		WritingUtil util = ctx.getBean(WritingUtil.class); // 스프링이 관리하는 빈으로부터 유틸타입을 받아쓰기
		util.write1();
		util.write2();
		util.write3();

		Messenger mgr = ctx.getBean(Messenger.class);
		String msg = mgr.getMessage();
		System.out.println("Messenger 객체로부터 받은 메세지:" + msg);
		
		mgr.sendGreeting("안녕하세요");
		mgr.sendGreeting("바보야 안녕");
		mgr.sendGreeting("또 만났군요!");

		

	}

}
