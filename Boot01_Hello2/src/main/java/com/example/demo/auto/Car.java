package com.example.demo.auto;

import org.springframework.stereotype.Component;

//component scan을 통해서 bean이 되도록한다.
@Component
public class Car {
	public void drive() {
		System.out.println("달려요!");
	}
}
