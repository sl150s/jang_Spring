package com.gura.spring02.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.dao.MemberDao;
import com.gura.spring02.member.dto.MemberDto;

@Controller
public class MemberController {
	
	//필요한 핵심 의존 객체를 주입 받는다.
	@Autowired
	private MemberDao dao;
	
	@RequestMapping("/member/list")
	public ModelAndView getList(ModelAndView mView) {
		List<MemberDto> list = dao.getList();
		
		//view page에 전달할 Model을 담는다 
		mView.addObject("list",list);
		
		//view page 정보도 담아서 
		mView.setViewName("member/list");
		
		//리턴해준다. 
		return mView;
	}
}
