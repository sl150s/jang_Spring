<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/insertform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원추가 폼(HttpServletRequest request 객체 만들기)</h1>
		<form action="${pageContext.request.contextPath}/member/insert" method="post">
			번호 <input type="text" name="num" />
			이름 <input type="text" name="name" />
			주소 <input type="text" name="addr" />
			<button type="submit">추가</button>
		</form>
		
		<h1>회원추가 폼2(매개변수 선언)</h1>
		<form action="${pageContext.request.contextPath}/member/insert2" method="post">
			번호 <input type="text" name="num" />
			이름 <input type="text" name="name" />
			주소 <input type="text" name="addr" />
			<button type="submit">추가</button>
		</form>
		
		<h1>회원추가 폼3(dto 활용)</h1>
		<form action="${pageContext.request.contextPath}/member/insert3" method="post">
			번호 <input type="text" name="num" />
			이름 <input type="text" name="name" />
			주소 <input type="text" name="addr" />
			<button type="submit">추가</button>
		</form>
	</div>
</body>
</html>