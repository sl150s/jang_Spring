package com.gura.spring02.member.Service;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.dto.MemberDto;

public interface MemberService {
	//회원 정보를 추가 해주ㅡㄴ 메소드 
	public void addMember(MemberDto dto);
	//회원 정보를 수정해주는 메소드
	public void updateMember(MemberDto dto);
	//회원 정보를 삭제해주는 메소두
	public void deleteMember(int num);
	//회원 한명의 전보를 인자로 전달한 ModelAndView 객체에 담아주는 메소드
	public void getMember(int num, ModelAndView mView);
	//회원 전체의 정보를 인자로 전달한 ModelAndView 객체에 담아주는 메소드
	public void getListMember(ModelAndView mView);
}
