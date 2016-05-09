package com.estsoft.jblog.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.estsoft.jblog.annotation.AuthUser;
import com.estsoft.jblog.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		System.out.println("in authuserhandlermethodargumentresolver");

		// parameter 유효성 확인
		if (supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}

		// session에서 authUser 가져오기
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		
		if (session == null) {
			return WebArgumentResolver.UNRESOLVED;
		}
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// annotation이 AuthUser인지 채크
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		if (authUser == null) {
			return false;
		}

		// annotation이 붙어있는 변수가 UserVo class 인지 확인
		if (parameter.getParameterType().equals(UserVo.class) == false) {
			return false;
		}

		return true;
	}

}
