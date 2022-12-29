<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지입니다.</h1>
		<ul>
			<c:forEach var="tmp" items="${noticeList}">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
		
		<h3>resources/mages 폴더 안에 있는 이미지</h3>
		<img src="${pageContext.request.contextPath}/resources/images/kim1.png" />
	</div>
</body>
</html>