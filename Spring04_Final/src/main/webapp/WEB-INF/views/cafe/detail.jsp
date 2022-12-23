<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/detail.jsp</title>
</head>
<body>
	<div class="container">
		<h3>카페 글 내용</h3>
		<ul>
			<li>
				<strong>작성자</strong>
				<p>${dto.writer}</p>
			</li>
			<li>
				<strong>제목</strong>
				<p>${dto.title}</p>
			</li>
			<li>
				<strong>내용</strong>
				<p>${dto.content}</p>
			</li>
			<li>
				<strong>조회수</strong>
				<p>${dto.viewCount}</p>
			</li>
		</ul>
		<button type="">수정하기</button>
		<<a href="${pageContext.request.contextPath}/cafe/list">목록</a>
	</div>
</body>
</html>