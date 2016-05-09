package com.estsoft.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.PostDao;
import com.estsoft.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	public List<PostVo> getList(Long category_no){
		List<PostVo> list = postDao.getList(category_no);
		return list;
	}
	
	public void write(PostVo vo){
		postDao.insert(vo);
	}
	
	public PostVo get(Long no){
		PostVo vo = new PostVo();
		vo.setNo(no);
		
		vo = postDao.get(vo);
		
		return vo;
	}
	
	public void delete(Long no){
		PostVo vo = new PostVo();
		vo.setNo(no);
		
		postDao.delete(vo);
	}
}
