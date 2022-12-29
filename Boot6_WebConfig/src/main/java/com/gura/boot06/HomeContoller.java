package com.gura.boot06;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *  [ jsp 페이지를 사용하기 위한 설정 ] 
 *  
 *  1. pom.xml에 tomcat-embed-jasper와 jstl dependency를 추가한다.
 *  2. webapp/WEB-INF/views 구조로 폴더를 src/main 하위에 만든다
 *  3. view page의 prefix와 suffix 설정을 추가한다.
 *     만일 application.properties에 설정할 거라면
 *     
 *     spring.mvc.view.prefix = /WEB-INF/views/
 *     spring.mvc.view.suffix = .jsp
 *     
 *     이 내용을 추가한다(추후에 설정을 다른 곳에서 해야함) 
 */
@Controller
public class HomeContoller {
	//get방식이며 최상위 요청에 관한 건 모두 이 메소드로 연결 
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		List<String> noticeList = new ArrayList<>();
		noticeList.add("Spring boot 시작입니다. ");
		noticeList.add("어쩌구..");
		noticeList.add("저쩌구..");
		
		request.setAttribute("noticeList", noticeList);
		return "home";
	}
	
	@GetMapping("/users/info")
	@ResponseBody
	public String info() {
		return "info ok!";
	}
	
	@GetMapping("/users/loginform")
	@ResponseBody
	public String loginform() {
		return "loginform ok!";
	}
}
