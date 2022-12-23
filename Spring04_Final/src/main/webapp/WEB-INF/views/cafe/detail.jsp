<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/detail.jsp</title>
</head>
<body>
	<div class="container">
		<%--만일 다음글(더 최신글)의 글번호가 0이 아니라면(다음글이 존재한다면) --%>
		<c:if test="${dto.nextNum ne 0 }">
			<a href="detail?num=${dto.nextNum}">다음글</a>
		</c:if>
		<%--만일 이전글( 더 예전글)의 글번호가 0이 아니라면(이전글이 존재한다면 --%>
		<c:if test="${dto.prevNum ne 0 }">
			<a href="detail?num=${dto.prevNum}">이전글 </a>
		</c:if>
		<h3>글 상세 보기</h3>
		<table>
			<tr>
				<th>글번호</th>
				<td>${dto.num}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.writer}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.title}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.viewCount}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.regdate}</td>
			</tr>
			<tr>
				<td colspan="2">
					<div>${dto.content}</div>
				</td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath}/cafe/list">목록</a>
		<c:if test="${sessionScope.id eq dto.writer }">
			<a href="updateform?num=${dto.num}">수정</a>
			<a href="javascript:" onclick="deleteConfirm()">삭제</a>
			<script>
	            function deleteConfirm(){
	               const isDelete=confirm("이 글을 삭제 하겠습니까?");
	               if(isDelete){
	                  location.href="delete?num=${dto.num}";
	               }
	            }
         	</script>
		</c:if>
		
		 
      
      
		
	</div>
</body>
</html>