package com.estsoft.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.UserVo;

public class BlogInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("in bloginterceptor prehandle");
		
		String uri = request.getRequestURI();
		String [] uriList = uri.split("/");
		if(uriList.length < 4){
			// 잘못된 주소(짧음)
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// id에 해당하는 블로그가 있는지 확인
		String id = uriList[3];
		BlogVo vo = blogService.get(id);
		if(vo == null){
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		if(uriList.length > 4){
			// admin 등 페이지 확인
			return adminUserCheck(request, response, handler, id);
		}
		
		return true;
	}
	
	private boolean adminUserCheck(HttpServletRequest request, HttpServletResponse response, Object handler, String id)
			throws Exception {
		
		System.out.println("in adminUserCheck");
		
		HttpSession session = request.getSession();
		
		// 세션이 없을 때
		if(session == null){
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// authUser가 없을 때
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null){
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// authUser가 blog의 주소와 다를 때
		if(!authUser.getId().equals(id)){
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		return true;
	}

}
