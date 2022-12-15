package com.gura.spring01;

import org.springframework.stereotype.Service;

//Service 라는 이노테이션을 사용해서 객체를 이미 생성해서 가지고 있음 (new RemoconServiceImpl)
@Service
public class RemoconServiceImpl implements RemoconService{
	@Override
	public void down() {
		// TODO Auto-generated method stub
		System.out.println("채널을 올려요!");
	}

	
	@Override
	public void up() {
		// TODO Auto-generated method stub
		System.out.println("채널을 내려요!");
	}
}
