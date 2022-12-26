package com.example.demo.util;

import org.springframework.stereotype.Component;

// component scan이 일어 났을 때 bean이 될 수 있도록 어노테이션 붙여넣기
@Component
public class WritingUtil {

	// 생성자
	public WritingUtil() {
		System.out.println("WritingUtil 생성자");
	}

	public void write1() {
		
		System.out.println("편지를 써요");
	}

	public void write2() {
		
		System.out.println("보고서를 써요");
	}

	public void write3() {
		
		System.out.println("일기를 써요");
	}
}