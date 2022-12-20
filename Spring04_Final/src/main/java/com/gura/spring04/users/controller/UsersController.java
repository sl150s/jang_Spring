package com.gura.spring04.users.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.users.dto.UsersDto;
import com.gura.spring04.users.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;

	/*
	 *  GET 방식 /users/signup_form 요청을 처리할 메소드 
	 *  - 요청방식이 다르면 실행되지 않는다. 
	 *  - 때로는 경로요청 뿐만 아니라 요청 방식도 구문해야할 때가 있다. 
	 *  - 이때, 사용하는 것이 method =RequestMethod.__ , value = "경로"
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
	
	//회원가입 요청처리
	@RequestMapping(method = RequestMethod.POST, value = "/users/signup")
	public ModelAndView signup(ModelAndView mView, UsersDto dto) {
		service.addUser(dto);
		mView.setViewName("users/signup");
		return mView;
	}
	
	//로그인 폼 요청 처리 
	@RequestMapping(method = RequestMethod.GET, value="/users/loginform")
	public String loginForm() {
		return "users/loginform";
	}
	
	//로그인 요청 처리
	@RequestMapping(method=RequestMethod.POST, value="/users/login")
	public ModelAndView login(UsersDto dto, HttpSession session, ModelAndView mView, String url) {
		/*
		 * 서비스에서 비즈니스 로직을 처리할 때 필요로 하는 객체를 컨트롤러에서 직접 전달을 해 주어야한다.
		 * 주로, HttpServeltRequest, HttpServletResponse, Httpsession, ModelAndView
		 * 등등의 객체이다. 
		 */
		service.loginProcess(dto, session);
		
		//로그인 후에 가야할 목적지 정보를 인코딩하지 않는 것과 인코딩한 것을 모두 ModelAndView 객체에 담고
		String encodeUrl=URLEncoder.encode(url);
		mView.addObject("url",url);
		mView.addObject("encodeUrl",encodeUrl);
		
		//view page로 forward 이동해서 응답한다. 
		mView.setViewName("users/login");
		
		return mView;
		
	}
}










