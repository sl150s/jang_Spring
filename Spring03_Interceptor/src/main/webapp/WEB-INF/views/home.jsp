<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
</head>
<body>
	<div class="container">
	
		<h1>인텍스 페이지 입니다. </h1>
		<ul>
			<li><a href="play">놀러가기</a></li>
			<li><a href="users/loginform">로그인</a></li>
			<li><a href="users/logout">로그아웃</a></li>
			<li><a href="users/info">개인정보(로그인 필요)</a></li>
			<li><a href="file/insertform">파일 업로드 테스트</a></li>
			<li><a href="cafe/insertform">스마트 에디터 테스트</a></li>
		</ul>
		<img src="${pageContext.request.contextPath}/resources/images/kim1.png" alt="김구라 사진" />
		<h3>공지사항</h3>
		<ul>
			<c:forEach var="tmp" items="${noticeList}">
				<li>${tmp}</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>