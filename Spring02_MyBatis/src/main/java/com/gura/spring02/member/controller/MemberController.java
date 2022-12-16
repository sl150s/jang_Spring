package com.gura.spring02.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.Service.MemberService;
import com.gura.spring02.member.dto.MemberDto;

@Controller
public class MemberController {
	
	//필요한 핵심 의존 객체를 주입 받는다.
	@Autowired
	private MemberService service;
	
	@RequestMapping("/member/update")
	public String update(MemberDto dto) {
		//서비스를 이용해서 회원 한명의 전보 수정
		service.updateMember(dto);
		return "member/update";
	}
	
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(int num, ModelAndView mView) {// ModelAndView 객체도 받을 수 있다.
		//서비스를 이용해서 회원 한 명의 전보를 ModeAndView 객체에 담는다.
		service.getMember(num, mView);
		//view page의 정보도 담아서
		mView.setViewName("member/updateform");
		//리턴해준다.
		return mView;
	}
	
	@RequestMapping("/member/delete")
	public String delete(int num) {
		//ㅅ비스를 이요해서 회원 함영의 정보 삭제 
		service.deleteMember(num);
		return "redirect:/member/list";
	}
	
	@RequestMapping("/member/list")
	public ModelAndView getList(ModelAndView mView) {
		//서비스를 이용해서 회원 전체 목록을 ModelAndView 객체에 담는다. 
		service.getListMember(mView);
		
//		List<MemberDto> list = dao.getList();
//		
//		//view page에 전달할 Model을 담는다 
//		mView.addObject("list",list);
		
		//view page 정보도 담아서 
		mView.setViewName("member/list");
		
		//리턴해준다. 
		return mView;
	}
	
	@RequestMapping("/member/insert")
	public String insert(MemberDto dto) { //폼 전송되는 name, addr이 자동으로 추출되어서 MemberDto에 담겨서 전달된다. 단, 자동으로 추출되는 기준은 조건이 맞아야한다 , dto의 필드
		//서비스를 이용해서 회우너 한명의 정보 추가 
		service.addMember(dto);
		// /WEB-INF/views/member/insert.jsp forword 이동하여 처리
		return "member/insert";
	}
	
	@RequestMapping("/member/insertform")
	public String inserform() {
		//WEB-INF/views/member/insertform.jsp 로 forward 이동해서 응답
		return "member/insertform";
	}
	
	
}
