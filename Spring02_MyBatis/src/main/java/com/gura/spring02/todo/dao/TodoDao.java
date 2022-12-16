package com.gura.spring02.todo.dao;

import java.util.List;

import com.gura.spring02.todo.dto.TodoDto;

public interface TodoDao {
	public List<TodoDto> getlist();
	public void insert(String content);
	public void delete(int num);
	public TodoDto getdata(int num);
	public void update(TodoDto dto);
	
}
