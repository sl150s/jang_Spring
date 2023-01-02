package com.gura.boot07.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.boot07.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert" ,dto);
	}

	//해당 아이디가 존재하는 여부를 리턴하는 메소드
	@Override
	public boolean isExist(String inputId) {
		//ID를 이용해서 select 해보면 null 혹은 null이 아니다. 
		UsersDto dto = session.selectOne("users.getData", inputId);
		//아이디 존재 여부를 알아내서 
		boolean isExist = dto==null ? false : true;
		//리턴해준다 
		return isExist;
	}

	@Override
	public UsersDto getData(String id) {
		/*
		 * mapper's namespace => users
		 * sql's id = getData
		 * parameterType => String 
		 * selectOne()메소드를 호출한 경우 resultType이 메소드의 리턴 type이 된다. 
		 * resultType => UsersDto
		 */
		UsersDto dto = session.selectOne("users.getData",id);
		return dto;
	}

	//비밀번호 수정
	@Override
	public void updatePwd(UsersDto dto) {
		session.update("users.updatePwd",dto);
	}

	//이메일과 프로필 수정 
	@Override
	public void update(UsersDto dto) {
		session.update("users.update",dto);
	}

	//아이디를 이용한 삭제
	@Override
	public void delete(String id) {
		session.delete("users.delete",id);
	}
	
	
	 
	
	
}
