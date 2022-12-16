<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/todo/list.jsp</title>
</head>
<body>
	<div class="container">
		<h1>할일 목록</h1>
		<<a href="${pageContext.request.contextPath}/todo/insertform">할일 추가</a>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>할일 내역</th>
					<th>날짜</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${list}">
					<tr>
						<td>${tmp.num}</td>
						<td>${tmp.content}</td>
						<td>${tmp.regdate}</td>
						<td><a href="updateform?num=${tmp.num}">수정</a></td>
						<td><a href="delete?num=${tmp.num}">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
</body>
</html>