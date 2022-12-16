<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/update.jsp</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<p><strong>${dto.num}</strong>회원정보가 수정되었습니다.</p>
		<a href="${pageContext.request.contextPath}/member/list">목록으로 이동</a>
	</div>
	
</body>
</html>