package com.gura.spring02.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.dto.MemberDto;
import com.gura.spring02.todo.dao.TodoDao;
import com.gura.spring02.todo.dto.TodoDto;

@Controller
public class TodoController {
	
	@Autowired
	public TodoDao dao;
	
	//할일 수정하기
	@RequestMapping("/todo/update")
	public String update(TodoDto dto) {
		dao.update(dto);
		return "todo/update";
	}
	
	
	//수정할 할일 리스트 한개 가져오기
	@RequestMapping("/todo/updateform")
	public ModelAndView updateform(int num, ModelAndView mView) {
		TodoDto dto = dao.getdata(num);
		mView.addObject("dto", dto);
		mView.setViewName("todo/updateform");
		return mView;
	}
	
	//할일 삭제하기 
	@RequestMapping("/todo/delete")
	public String delete(int num) {
		dao.delete(num);
		//System.out.println(num);
		return "redirect:/todo/list";
	}
	
	//할일 추가
	@RequestMapping("/todo/insert")
	public String insert(String content) {
		dao.insert(content);
		return "todo/insert";
	}
	
	//할일 추가 폼 
	@RequestMapping("/todo/insertform")
	public String insertform() {
		return "todo/insertform";
	}
	
	//할일 목록 가져오기 
	@RequestMapping("/todo/list")
	public ModelAndView getlist(ModelAndView mView) {
		//전체 회원 목록 보내기
		List<TodoDto> list = dao.getlist();
		
		mView.addObject("list",list);
		mView.setViewName("todo/list");
		
		return mView; 
	}
}
