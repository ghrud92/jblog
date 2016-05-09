package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.UserDao;
import com.estsoft.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	public void insert(UserVo vo){
		dao.insert(vo);
	}
	
	public UserVo login(UserVo vo){
		UserVo authUser = dao.get(vo);
		return authUser;
	}
	
	public boolean isExist(String id){
		UserVo vo = dao.get(id);
		return vo != null;
	}
}
