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
var getListAjax = function(){
	// category 출력하는 ajax
	$.ajax({
		url:"${pageContext.request.contextPath}/blog/${blogVo.user_id}/category/ajax-list",
		type:"get",
		dataType:"json",
		data:"",
		success:function(response){
			if(response.result != "success"){
				return;
			}
			var $count = response.data.length;
			$.each(response.data, function(index, vo){
				renderHtml(vo, $count);
				$count--;
			})
			
			console.log(response);
		},
		error:function(xhr/*XMLHttpRequest*/, status, error){
			console.error(status + ":" + error);
		}
	})
}

$(function(){
	
	$("#header-category a").remove();
	$("#header-category").addClass("selected").append("<a>카테고리</a>");
	
	// category 출력하는 ajax
	getListAjax();
	
	// category 삽입 버튼 클릭
	$("#submit").click(function(){
		var $name = $("#name").val();
		var $desc = $("#desc").val();
		
		$.ajax({
			url:"${pageContext.request.contextPath}/blog/${blogVo.user_id}/category/ajax-insert?name="
					+ $name + "&description=" + $desc + "&blog_no=${blogVo.blog_no}",
			type:"get",
			dataType:"json",
			data:"",
			success:function(response){
				if(response.result != "success"){
					return;
				}
				
				$(".categorylist").remove();
				
				getListAjax();
			},
			error:function(xhr, status, error){
				console.error(status + ":" + error);
			}
		})
	})
	
	// category 삭제 버튼 클릭
	$(document).on("click",".delete",function(){
		event.preventDefault();
		var no = $(this).attr("data-no");
		$.ajax({
			url:"${pageContext.request.contextPath}/blog/${blogVo.user_id}/category/ajax-delete?no=" + no,
			type:"get",
			dataType:"json",
			data:"",
			success:function(response){
				if(response.result != "success"){
					return;
				}

				$(".categorylist").remove();
				
				getListAjax();
			},
			error:function(xhr/*XMLHttpRequest*/, status, error){
				console.error(status + ":" + error);
			}
		})
	})
})

var renderHtml = function(vo, $count){
	var html = 	"<tr class='categorylist' id='category-" + vo.no + "'><td>" + $count + "</td>" +
				"<td>" + vo.name + "</td>" +
				"<td>" + vo.posting + "</td>" +
				"<td>" + vo.description + "</td>" +
				"<td><a href='#' class='delete' data-no='" + vo.no + "'>" +
				"<img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a></td></tr>" 
	$(".admin-cat").append(html);
}
</script>
</head>
<body>
	<div id="container">
		<c:import url="/views/include/blog-header.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/views/include/admin-header.jsp"></c:import>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" id="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc" id="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" id="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<c:import url="/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>