package com.acorn.basic.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// component scan을 통해서 spring이 관리하는 bean이 될수 있도록 어노테이션 붙이기
@Repository
public class MemberDaoImpl implements MemberDao{
	// application.properties에 설정한 정보가 잘 동작한다면 SqlSession객체가 자동 주입된다. 
	@Autowired 
	private SqlSession session;
}
