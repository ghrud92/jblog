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
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#header-admin a").remove();
	$("#header-admin").addClass("selected").append("<a>기본설정</a>");
	
	// 이미지 파일 변경될 시
	$("#imgfile").change(function(){
		var $img = $("#imgfile").files;
		$.ajax({
			url:"${pageContext.request.contextPath }/blog/${blogVo.user_id}/ajax-imgmodify",	//요청 url
			type:"post",							// 통신 방식
			dataType:"json",					// 수신 데이터 타입
			data:"imgfile=" + $img,							// post 방식인 경우 서버에 전달할 파라미터 데이터
												// ex) a=checkemail&email=ghrud92@gmail.com
//			contentType:"application/json",		// 보내는 데이터가 json 형식인 경우. 반드시 post 방식으로 보내야한다.
												// ex) "{ "a":"checkemail", "email":"ghrud92@gmail.com" }"
			success:function(response){			// 성공시 callback
				if(response.result != "success"){
					return;
				}
			},
			error:function(jqXHR, status, error) {	// 실패시 callback
				console.error(status + ":" + error);
			}
		})
	})
})
</script>
</head>
<body>
	<div id="container">
		<c:import url="/views/include/blog-header.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/views/include/admin-header.jsp"></c:import>
				<form action="${pageContext.request.contextPath }/blog/${blogVo.user_id }/modifyblog" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="user_id" value="${blogVo.user_id }">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="blog_name" value="${blogVo.blog_name }"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img src="${blogVo.img}"></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" id="imgfile" name="imgfile"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>
		<c:import url="/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>