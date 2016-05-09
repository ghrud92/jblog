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
	$("#delete-btn").click(function(){
		var $pno = $("#delete-btn").val();
		
	})
})
</script>
</head>
<body>
	<div id="container">
		<c:import url="/views/include/blog-header.jsp"></c:import>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>${postVo.content }<p>
					<form action="${pageContext.request.contextPath }/blog/${blogVo.user_id}/deletepost" method="post" >
						<input type="hidden" name="no" value="${postVo.no }"/>
						<input type="submit" value="삭제">
					</form>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList }" var="pList" varStatus="status">
						<li><a href="${pageContext.request.contextPath }/blog/${blogVo.user_id}?cno=${pList.category_no}&pno=${pList.no}">${pList.title }</a> <span>${pList.reg_date }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${blogVo.img }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList }" var="cList" varStatus="status">
					<li><a href="${pageContext.request.contextPath }/blog/${blogVo.user_id}?cno=${cList.no}">${cList.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<c:import url="/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>