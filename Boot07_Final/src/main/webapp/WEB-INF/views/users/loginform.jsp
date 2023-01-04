<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/loginform.jsp</title>
<link
	href="${pageContext.request.contextPath}/web/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/web/css/sign-in.css"
	rel="stylesheet">
</head>
<body class="text-center">
	<div class="container">
		<main class="form-signin w-100 m-auto">
			<form action="${pageContext.request.contextPath}/users/login"
				method="post">
				<img class="mb-4"
					src="${pageContext.request.contextPath}/web/assets/brand/bootstrap-logo.svg"
					alt="" width="72" height="57">
				<h1 class="h3 mb-3 fw-normal">로그인 폼</h1>
				<c:choose>
					<c:when test="${ empty param.url }">
						<!-- url이 비어있다면 home으로 -->
						<input type="hidden" name="url"
							value="${pageContext.request.contextPath}/" />
					</c:when>
					<c:otherwise>
						<!-- url이 비어있지 않다면 원래 가려던 링크로 -->
						<input type="hidden" name="url" value="${param.url }" />
					</c:otherwise>
				</c:choose>

				<div class="form-floating">
					<input type="text" name="id" id="floatingInput"
						class="form-control"  placeholder="아이디 입력..." value="${cookie.savedId.value}"/>
					<label for="floatingInput">아이디</label> 
				</div>
				<div class="form-floating">
					<input type="password" name="pwd"
						id="floatingPassword" class="form-control" placeholder="비밀번호 입력..." value="${cookie.savedPwd.value}"/>
					<label for="floatingPassword">비밀번호</label> 
				</div>
				<div class="checkbox mb-3">
					<label> 
						<!-- 체크박스를 체크하지 않으면 isSave 라는 파라미터 값으로 넘어오는 문자열을 null이고
						체크를 하면 isSave라는 파라미터 값으로 넘어오는 문자열은 "yes" 이다 -->
						<input type="checkbox" value="remember-me" name="isSave">
						로그인 정보 저장
					</label>
				</div>
				<button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
				<p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
			</form>
		</main>
	</div>
</body>
</html>