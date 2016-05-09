<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function() {
	$("#id-alert").hide();
	$("#agree-alert").hide();
	
	$("#join-form").submit(function(){
		
		$("#id-alert").hide();
		$("#agree-alert").hide();
		
		if($("#img-checkemail").is(":visible") == false){
			$("#id-alert").show();
			return false;
		}
		
		if($("#agree-prov").is(":checked") == false){
			$("#agree-alert").show();
			return false;
		}
		
		return true;
	})
	
	$("#btn-checkemail").click(function(){
		var $id = $("#blog-id").val();
		if($id == ""){
			return;
		}
		
		$.ajax({
			url:"${pageContext.request.contextPath }/user/checkid?id=" + $id,	//요청 url
			type:"get",							// 통신 방식
			dataType:"json",					// 수신 데이터 타입
			data:"",							// post 방식인 경우 서버에 전달할 파라미터 데이터
												// ex) a=checkemail&email=ghrud92@gmail.com
//			contentType:"application/json",		// 보내는 데이터가 json 형식인 경우. 반드시 post 방식으로 보내야한다.
												// ex) "{ "a":"checkemail", "email":"ghrud92@gmail.com" }"
			success:function(response){			// 성공시 callback
				if(response.result != "success"){
					return;
				}
				if(response.data == true){
					alert("이미 존재하는 이메일입니다");
					$("#blog-id").val("").focus();
					return;
				}
				
				$("#btn-checkemail").hide();
				$("#img-checkemail").show();
			},
			error:function(jqXHR, status, error) {	// 실패시 callback
				console.error(status + ":" + error);
			}
		})
	})
	
	$("#blog-id").change(function(){
		$("#btn-checkemail").show();
		$("#img-checkemail").hide();
	})
})
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/views/include/main-header.jsp"></c:import>
		<form class="join-form" id="join-form" method="post"
			action="${pageContext.request.contextPath }/user/join">
			<strong id="id-alert" style="color: red;"><br>중복 확인을 해주세요</strong>
			<strong id="agree-alert" style="color: red;"><br>약관에 동의 해주세요</strong>
			<label class="block-label" for="name">이름</label>
			<input id="name" name="name" type="text" value="${vo.name }">

			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('name') }">
					<c:set var="errorName1"	value="${errors.getFieldError('name').codes[0] }" />
					<br><strong style="color: red;">
						<spring:message code="${errorName1}" text="${errors.getFieldError( 'name' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>

			<label class="block-label" for="blog-id">아이디</label> 
			<input id="blog-id" name="id" type="text" value="${vo.id }"> 
			<input id="btn-checkemail" type="button" value="id 중복체크"> 
			<img id="img-checkemail" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">
			
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('id') }">
					<c:set var="errorName2"	value="${errors.getFieldError('id').codes[0] }" />
					<br><strong style="color: red;">
						<spring:message code="${errorName2}" text="${errors.getFieldError( 'id' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>
			
				
			<label class="block-label" for="password">패스워드</label> 
			<input id="password" name="passwd" type="password" />
			
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('passwd') }">
					<c:set var="errorName3"	value="${errors.getFieldError('passwd').codes[0] }" />
					<br><strong style="color: red;">
						<spring:message code="${errorName3}" text="${errors.getFieldError( 'passwd' ).defaultMessage }" />
					</strong>
				</c:if>
			</spring:hasBindErrors>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
