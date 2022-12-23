package com.gura.spring04.cafe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.cafe.dto.CafeDto;
import com.gura.spring04.cafe.service.CafeService;

@Controller
public class CafeController {
	
	@Autowired
	private CafeService service;
	
	@RequestMapping("/cafe/list")
	public String list(HttpServletRequest request) {
		service.getList(request);
		return "cafe/list";
	}
	
	@RequestMapping("/cafe/detail")
	public ModelAndView detail(HttpServletRequest request, int num, ModelAndView mView) {
		service.getDetail(request);
		mView.addObject(num);
		mView.setViewName("cafe/detail");
		return mView;
	}
	
	@RequestMapping("/cafe/insertform")
	public String insertform() {
		return "cafe/insertform";
	}
	
	@RequestMapping("cafe/insert")
	public String insert(CafeDto dto, HttpSession session) { 
		//글 작성자는 세션에서 얻어낸다.
		String writer = (String)session.getAttribute("id");
		//dto는 글의 제목과 내용만 있으므로 글 작성자는 직접 넣어준다.
		dto.setWriter(writer);
		service.saveContent(dto);
		return "cafe/insert";
	}

}
