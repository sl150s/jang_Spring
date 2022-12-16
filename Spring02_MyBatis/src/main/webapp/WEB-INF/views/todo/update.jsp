<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/todo/update.jsp</title>
</head>
<body>
	<div class="container">
		<p><strong>${list.num}</strong> 번호 수정완료했습니다.</p>
		<a href="${pageContext.request.contextPath}/todo/list">목록으로 돌아가기</a>
	</div>
</body>
</html>