package com.gura.spring01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	@Autowired //spring bean container에 RemoconService type 객체가 있으면 자동 주입된다.(DI)
	private RemoconService service; //RemoconServiceImpl 객체의 참조값을 주입 받을 필드
	
	//RemoconService 를 쓰는 이유는 클래스 타입인 (RemoconServiceImpl)의 의존하는 것이 아닌
	// 인터페이스 타입 (RemoconService)에 의존하게 하려고 하기 위함이다.
	// ex) RemoconServiceImpl.java 코드 참고 
	
	@ResponseBody
	@RequestMapping("/di/up")
	public String diup() {
		/*
		 * 채널을 올리는 로직을 수행할 때 필요한 객체는
		 * 
		 */
		//이렇게 직접 불러올때는 유지보수 관점에서 좋지 않음 .
		//RemoconServiceImpl service = new RemoconServiceImpl();
		service.up();
		return "up ok!";
	}
	
	@ResponseBody
	@RequestMapping("/di/down")
	public String didown() {
		service.down();
		return "down ok!";
	}
	
	
	
	
	
	
	@RequestMapping("/di/test")
	public String di() {
		return "di/test";
	}
	
	@RequestMapping("/move/test")
	public String test() {
		/*
		 *  Controller 메소드에서 return type을 String으로 설정한 후
		 *  문자열을 리턴해주면 해당 문자열은 view page(jsp)의 위치가 된다. 
		 *  즉 해당 jsp 페이지로 자동 forward 이동되어서 응답하게 된다.
		 */
		
		// /WEB-INF/views/move/test.jsp 페이지로 forward 이동
		return "move/test";
	}
	
	@RequestMapping("/move/update")
	public String update() {
		//무언가 수정했다고 가정하기
		System.out.println("무언가 수정했습니다.");
		
		//클라이언트에게 새로운 경로로 요청을 다시하라고 강요하기(redirect 이동)
		

		//"redirect: 리다일렉트 이동할 절대 경로" -> (context path는 쓰지 않는다)
		return "redirect:/move/test";
	}
	
	
	@RequestMapping("/move/fortune")
	public ModelAndView fortune() {
		//Model(데이터) 과 view page(페이지 경로)의 위치를 동시에 담을 수 있는 ModelAndView 객체 생성 
		ModelAndView mView = new ModelAndView();
		
		//view page에 전달할 모델(데이터)라고 가정하자
		String fortuneToday="동쪽으로 가면 귀인을 만나요!";
		
		//HttpServletRequest객체에 담는 대신 
		//.addObject(key, value) 형태로 ModelAndView 객체에 담으면된다. 
		mView.addObject("fortuneToday", fortuneToday);
		
		//view page의 위치를 담는다. 
		mView.setViewName("move/fortune");
		
		//리턴 해주기 
		return mView;
		
	}
	
	
	//view page를 ModelAndView 객체 생성 시 생성자의 인자로 전달할 수 있다.
	@RequestMapping("/move/fortune2")
	public ModelAndView fortune2() {
		
		//view page(경로)의 정보를 객체 생성할 때 바로 전달하 수 있다. 
		ModelAndView mView = new ModelAndView("move/fortune");
		//view page에 전달할 모델(데이터)라고 가정하자
		String fortuneToday="동쪽으로 가면 귀인을 만나요!";
		
		//HttpServletRequest객체에 담는 대신 
		//.addObject(key, value) 형태로 ModelAndView 객체에 담으면된다. 
		mView.addObject("fortuneToday", fortuneToday);
		
		
		//리턴 해주기 
		return mView;
	}
	
	@RequestMapping("/move/fortune3")
	public ModelAndView fortune3(ModelAndView mView) {
		//view page에 전달할 모델(데이터)라고 가정하자
		String fortuneToday="동쪽으로 가면 귀인을 만나요!";
		
		//HttpServletRequest객체에 담는 대신 
		//.addObject(key, value) 형태로 ModelAndView 객체에 담으면된다. 
		mView.addObject("fortuneToday", fortuneToday);
		
		//view page의 위치를 담는다. 
		mView.setViewName("move/fortune");
		
		//리턴 해주기 
		return mView;
		
	}
}
