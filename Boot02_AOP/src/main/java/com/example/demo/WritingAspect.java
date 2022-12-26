package com.example.demo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //aspect 역할을 하기 위한 어노테이션 
@Component //bean으로 만들기 위한 어노테이션
public class WritingAspect {
	
	@Before("execution(void write*())")  //aspect 가 적용된 메소드가 호출되기 직전에 할 작업은 여기서 한다
	public void prepare() {
		System.out.println("파란색 pen을 준비해요!");
	}
	
	
	@After("execution(void write*())") //aspect가 적용된 메소드가 리턴된 직후에 할 작업은 여기서한다. 
	public void end() {
		System.out.println("파란색 pen을 닫고 마무리해요!");
	}
	
	
	/*
	 *    -Aspectj Expression
	 * 
	 *    1. execution(* *(..)) => 접근 가능한 모든 메소드가 
	 *       point cut
	 *    2. execution(* test.service.*.*(..)) 
	 *       => test.service 패키지의 모든 메소드 point cut
	 *    3. execution(void insert*(..))
	 *       =>리턴 type 은 void 이고 메소드명이 
	 *       insert 로 시작하는 모든 메소드가 point cut
	 *    4. execution(* delete*(*))
	 *       => 메소드 명이 delete 로 시작하고 인자로 1개 전달받는 
	 *      메소드가 point cut (aop 가 적용되는 위치)
	 *    5. execution(* delete*(*,*))
	 *       => 메소드 명이 delete 로 시작하고 인자로 2개 전달받는 
	 *      메소드가 point cut (aop 가 적용되는 위치)
	 *  6. execution(String update*(Integer,*))
	 *      => 메소드 명이 update 로 시작하고 리턴 type 은 String
	 *      메소드의 첫번째 인자는 Integer type, 두번째 인자는 아무 type 다되는 
	 *      메소드가 point cut (aop 가 적용되는 위치)
	 */
	
	
}
