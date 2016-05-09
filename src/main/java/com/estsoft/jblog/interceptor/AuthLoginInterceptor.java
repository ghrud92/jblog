package com.estsoft.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.jblog.service.UserService;
import com.estsoft.jblog.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		
		// 파라미터 입력
		String id = request.getParameter("id");
		String passwd = request.getParameter("password");
		
		UserVo vo = new UserVo();
		vo.setId(id);
		vo.setPasswd(passwd);
		
		// 로그인 확인
		UserVo authUser = userService.login(vo);
		if(authUser == null){
			response.sendRedirect(request.getContextPath() + "/user/loginform?result=false");
			return false;
		}
		
		// 성공시 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());
		
		return false;
	}

}
