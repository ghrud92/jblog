package com.estsoft.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getList(Long category_no){
		List<PostVo> list = sqlSession.selectList("post.get_list", category_no);
		return list;
	}
	
	public void insert(PostVo vo){
		sqlSession.insert("post.insert", vo);
	}
	
	public PostVo get(PostVo vo){
		vo = sqlSession.selectOne("post.get",vo);
		return vo;
	}
	
	public void delete(PostVo vo){
		sqlSession.delete("post.delete", vo);
	}
}
