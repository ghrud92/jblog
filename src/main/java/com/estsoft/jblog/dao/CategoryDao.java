package com.estsoft.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getList(Long blog_no){
		List<CategoryVo> list = sqlSession.selectList("category.get_list", blog_no);
		return list;
	}
	
	public CategoryVo insert(CategoryVo vo){
		sqlSession.insert("category.insert", vo);
		return vo;
	}
	
	public void delete(CategoryVo vo){
		sqlSession.delete("category.delete", vo);
	}
}
