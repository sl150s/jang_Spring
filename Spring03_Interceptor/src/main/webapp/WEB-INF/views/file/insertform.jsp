<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/insertform.jsp</title>
</head>
<body>
	<div class="container">
		<h3>파일 업로드 폼 1 (Conroller.java 에서 전송값을 바로 인자로 받기)</h3>
		<form action="${pageContext.request.contextPath}/file/upload" method="post"
			enctype="multipart/form-data">
			제목 <input type="text" name="title" /><br/>
			첨부파일 <input type="file" name="myFile" /><br/>
			<button type="submit">업로드</button>
		</form>
		
		
		<h3>파일 업로드 폼 2 </h3>
		<form action="${pageContext.request.contextPath}/file/upload2" method="post"
			enctype="multipart/form-data">
			제목 <input type="text" name="title" /><br/>
			첨부파일 <input type="file" name="myFile" /><br/>
			<button type="submit">업로드</button>
		</form>
	</div>
</body>
</html>