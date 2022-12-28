package com.gura.boot03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 *  Spring Boot Web은 내장된 tomcat 서버가 있다. 
 *  그래서 따로 tomcat 서버를 잡을 필요가 없다. > 내장되어있으면 내마음대로 커스텀 하기가 힘들다는 단점이 있다.
 * 
 */
@SpringBootApplication
public class Boot03ControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot03ControllerApplication.class, args);
	}

}
