package com.estsoft.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Long insert(BlogVo vo){
		sqlSession.insert("blog.insert_blog", vo);
		return vo.getBlog_no();
	}
	
	public BlogVo get(BlogVo vo){
		vo = sqlSession.selectOne("get_blog", vo);
		return vo;
	}
	
	public void update(BlogVo vo){
		sqlSession.update("blog.update", vo);
	}
}
