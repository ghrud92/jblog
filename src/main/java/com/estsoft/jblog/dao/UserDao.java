package com.estsoft.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo){
		sqlSession.insert("user.insert", vo);
	}
	
	// 로그인용
	public UserVo get(UserVo vo){
		vo = sqlSession.selectOne("user.get", vo);
		return vo;
	}
	
	// id 채크용
	public UserVo get(String id){
		UserVo vo = sqlSession.selectOne("user.isExist", id);
		return vo;
	}
}
