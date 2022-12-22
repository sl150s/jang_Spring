<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${ empty sessionScope.id}">
				<a href="${pageContext.request.contextPath}/users/loginform">로그인</a>
				<a href="${pageContext.request.contextPath}/users/signup_form">회원가입</a>
			</c:when>
			<c:otherwise>
				<p>
					<a href="${pageContext.request.contextPath}/users/info">${sessionScope.id }</a>
					로그인중... <a href="${pageContext.request.contextPath}/users/logout">로그아웃</a>
				</p>
			</c:otherwise>
		</c:choose>
		<h1>인텍스 페이지 입니다.</h1>
		<ul>
			<li><a href="get_msg">@ResponseBody 어노테이션 테스트 </a></li>
			<li><a href="get_person">한명의 정보 </a></li>
			<li><a href="get_user">회원 한명의 정보 </a></li>
			<li><a href="get_friends">친구목록</a></li>
			<li><a href="get_users">회원 목록</a></li>
			<li><a href="file/list">자료실 목록보기</a></li>
		</ul>
		<ul>
			<c:forEach var="tmp" items="${noticeList}">
				<li>${tmp}</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>