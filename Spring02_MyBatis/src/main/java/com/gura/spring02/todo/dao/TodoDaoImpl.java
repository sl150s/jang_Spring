package com.gura.spring02.todo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring02.todo.dto.TodoDto;

@Repository
public class TodoDaoImpl implements TodoDao{
	
	@Autowired
	public SqlSession session;
	
	//수정(업데이트)하기
	@Override
	public void update(TodoDto dto) {
		session.update("todo.todo_update",dto);
		
	}
	
	//수정할 할일 목록 하나 가져오기
	@Override
	public TodoDto getdata(int num) {
		TodoDto dto = session.selectOne("todo.todo_getdata",num);
		return dto;
	}
	
	//삭제하기
	@Override
	public void delete(int num) {
		session.delete("todo.todo_delete",num);
	}
	
	//전체 할일목록 
	@Override
	public List<TodoDto> getlist() {
		//전체 회원 목록을 가져오기
		List<TodoDto> list = session.selectList("todo.todo_getlist");
		return list;
	}
	
	//할일추가
	@Override
	public void insert(String content) {
		session.insert("todo.todo_insert", content);
	}
}
