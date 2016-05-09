<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="admin-menu">
	<li id="header-admin"><a href="${pageContext.request.contextPath }/blog/${blogVo.user_id }/manage">기본설정</a></li>
	<li id="header-category"><a href="${pageContext.request.contextPath }/blog/${blogVo.user_id }/category">카테고리</a></li>
	<li id="header-writeform"><a href="${pageContext.request.contextPath }/blog/${blogVo.user_id }/writeform">글작성</a></li>
</ul>