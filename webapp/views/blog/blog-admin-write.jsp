<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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

	$("#alert-title").hide();
	$("#alert-content").hide();
	
	$("#header-writeform a").remove();
	$("#header-writeform").addClass("selected").append("<a>글작성</a>");
	
	// Validation
	$("#writeform").submit(function(){
		
		$("#alert-title").hide();
		$("#alert-content").hide();
		
		if($("#title").val() == ""){
			$("#alert-title").show();
			$("#title").focus();
			return false;
		}
		
		return true;
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
				<strong id="alert-title" style="color:red;">&nbsp;&nbsp;제목을 입력하세요</strong>
				
				<spring:hasBindErrors name="postVo">
					<c:if test="${errors.hasFieldErrors('content') }">
						<c:set var="errorName1" value="${errors.getFieldError('content').codes[0] }"/>
						<strong style="color:red;">
							<spring:message code="${errorName1}" text= "${errors.getFieldError( 'content' ).defaultMessage }" />
						</strong>
					</c:if>
				</spring:hasBindErrors>
				
				<form id="writeform" action="${pageContext.request.contextPath }/blog/${blogVo.user_id}/write" method="get">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title" id="title" value="${postVo.title }">
				      			<select name="category_no">
				      				<c:forEach items="${categoryList }" var="categoryVo" varStatus="status">
				      					<option value="${categoryVo.no }">${categoryVo.name }</option>
				      				</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="content" id="content"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		<c:import url="/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>