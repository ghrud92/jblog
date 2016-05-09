package com.estsoft.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.CategoryDao;
import com.estsoft.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> getList(Long blog_no){
		List<CategoryVo> list = categoryDao.getList(blog_no);
		return list;
	}
	
	public void defaultInsert(Long blog_no){
		CategoryVo vo = new CategoryVo();
		vo.setBlog_no(blog_no);
		vo.setName("미분류");
		vo.setDescription("카테고리를 지정하지 않은 경우");
		vo.setPosting(0);
		
		categoryDao.insert(vo);
	}
	
	public void delete(Long no){
		CategoryVo vo = new CategoryVo();
		vo.setNo(no);
		
		categoryDao.delete(vo);
	}
	
	public CategoryVo insert(CategoryVo vo){
		vo.setPosting(0);
		vo = categoryDao.insert(vo);
		
		return vo;
	}
}
