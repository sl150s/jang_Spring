package com.gura.boot03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController2 {
	
	//@RestController를 사용하면 @ResponseBody가 기본 효과다
	//GET 방식 /hello2 요청을 해오면 "hello2"라는 문자열이 바로 응답된다(@ResponseBody 효과)
	@RequestMapping(method=RequestMethod.GET , value="/hello2")
	public String hello2() {
		return "hello2";
	}
	
	//위의 맵핑 바익을 좀더 간단히 할 수 있다.
	@GetMapping("/hello3")
	public String hello3() {
		return "hello3";
	}
	
}
