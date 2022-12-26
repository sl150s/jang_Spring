package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.demo.aspect.MessengerAspect;
import com.example.demo.util.Messenger;

@Configuration
@EnableAspectJAutoProxy 
public class JavaConfig {
	
	@Bean
	public MessengerAspect aspect() {
		return new MessengerAspect();
	}
	
	//Messenger 객체가 spring이 관리하는 bean이 될 수 있도록
	@Bean
	public Messenger myMessenger() {
		return new Messenger();
	}
}
