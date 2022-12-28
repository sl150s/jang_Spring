<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<style>
	img {width:100%}	
</style>
</head>
<body>
	<div class="container">
		<a href="insertform">사진 업로드하기</a>
		<ul class="row ">
			<c:forEach var="tmp" items="${list}">
				<li class="col-md-3 col-sm-6">
					<div>
						<a href="detail?num=${list.num}">
							<img src="${pageContext.request.contextPath}/resources/upload/apple.jpg" alt="" />
						</a>
					</div>
				</li>
			</c:forEach>
			
			
		</ul>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>