<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#alert-id").hide();
	$("#alert-password").hide();
	
	$(".login-form").submit(function(){
		
		$("#alert-id").hide();
		$("#alert-password").hide();
		
		if($("#id").val() == ""){
			$("#alert-id").show();
			return false;
		}
		
		if($("#password").val() == ""){
			$("#alert-password").show();
			return false;
		}
		
		return true;
	})
})
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/views/include/main-header.jsp"></c:import>
		<form class="login-form" method="post" action="${pageContext.request.contextPath }/user/login">
			<c:if test="${param.result == false }"><strong style="color:red;">아이디와 비밀번호를 확인해</strong></c:if>
      		<label>아이디</label> <input type="text" name="id" id="id">
      		<strong id="alert-id">아이디를 입력하세요.</strong>
      		<label>패스워드</label> <input type="text" name="password" id="password" type="password">
      		<strong id="alert-password">패스워드를 입력하세요.</strong>
      		<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>
