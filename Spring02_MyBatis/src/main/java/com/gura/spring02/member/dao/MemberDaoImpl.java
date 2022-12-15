package com.gura.spring02.member.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gura.spring02.member.dto.MemberDto;

// component scan을 통해서 Spring이 관리하는 bean이 될 수 있도록 어노테이션 붙여놓기
// dao는 일반적으로 @Repository 를 사용한다 
@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	private SqlSession session; // selsect, insert, update, delete 작업을 하기 위한 핵심 의존 객체 
	
	@Override
	public MemberDto getData(int num) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void insert(MemberDto dto) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<MemberDto> getList() {
		/*
		 * mapper's namespace => member
		 * sql's id => getList
		 * resultType => MemberDto
		 * return type => List
		 */
		
		List<MemberDto> list = session.selectList("member.getList");
		return list;
	}
	
	@Override
	public void update(MemberDto dto) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		
	}
}
