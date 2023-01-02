package com.gura.boot07.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 *  Spring MVC가 동작 중에 특정 type의 예외가 발생하면 해당 예외를 여기서 처리할 수 있따.
 */

@ControllerAdvice
public class ExceptionController {
	//spring framework가 동작하는 중에 NotDeleteException type의 
	//예외가 발생하면 호출되는 메소드
	@ExceptionHandler(NotDeleteException.class)
	public ModelAndView notDelete(NotDeleteException nde) { //메소드의 인자로 예외 객체가 전달된다. 
		ModelAndView mView = new ModelAndView();
		//exception 이라는 키값으로 예외 객체를 담고 
		mView.addObject("exception", nde);
		//view page (/WEB-INF/views/error/info.jsp)로 forward 이동해서 예외정보응답하기.
		mView.setViewName("error/info");
		return mView;
	}
	
	
	
	/*
	 * - Spring 트랜젝션 설정 방법 
	 * 1. pon.xml에 spring - tx dependency 추가 
	 * 2. servlet-context.xml에 transaction 설정 추가 
	 * 3. 트랜젝션을 관리할 서비스의 메소드에 @Transactional 어노테이션 붙이기
	 * 
	 * - 프로그래머의 의도 하에서 트랜젝션에 영향을 주는 Exception을 발생시키는 방법
	 * 
	 * DataAccessException 클래스를 상속받은 클래스를 정의하고
	 * 
	 * 예) class MyException extends DataAccessExecption{ }
	 * 
	 * throw new MyException("예외 메세지");
	 * 
	 * 예외를 발생시킬 조건이라면 위와 같이 예외를 발생시켜서 트랜젝션이 관리되도록 한다.
	 */
	
	
	@ExceptionHandler(DeliveryException.class)
	public ModelAndView delivery(DeliveryException de) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("exception",de);
		mView.addObject("info","내년부터 제주도도 배송가능하도록 노력하겠습니다 ");
		mView.setViewName("error/delivery");
		return mView;
	}
	
	//DB관련 작업을 하다가 발생하는 모든 예외를 처리하는 컨트롤러
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView dataAccess(DataAccessException dae) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("exception",dae);
		mView.setViewName("error/info");
		return mView;
	}
}
