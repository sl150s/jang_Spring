<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/list.jsp</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath }/cafe/insertform">업로드
			하기</a>
		<h3>카페글 목록 보기</h3>
		<table class="table table-striped">
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>조회수</th>
					<th>등록일</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${list}">
					<tr>
						<td>${tmp.num }</td>
						<td>${tmp.writer }</td>
						<td><a href="detail?num=${tmp.num}">${tmp.title }</a></td>
						<td>${tmp.viewCount }</td>
						<td>${tmp.regdate }</td>
						<td>
							<c:if test="${ tmp.writer eq sessionScope.id}">
								<a href="javascript:deleteConfirm(${tmp.num})">삭제</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav>
			<ul class="pagination">
				<%--
               startPageNum 이 1 이 아닌 경우에만 Prev 링크를 제공한다. 
               
               //검색 후 페이지를 넘겨도(다른페이지로 넘어가도) 초기화 되지 않기 위해선 아래 코드 작성 
               &condition=${condition}&keyword=${encodedK}
               //encodedK : 인코딩된 키워드(특수문자를 검색할수도 있기 때문)
             --%>
				<c:if test="${startPageNum ne 1}">
					<li class="page-item"><a class="page-link"
						href="list?pageNum=${startPageNum-1 }&condition=${condition}&keyword=${encodedK}">Prev</a></li>
				</c:if>
				<c:forEach var="i" begin="${ startPageNum}" end="${endPageNum }">
					<!-- startPageNum에서 1씩 증가, endPageNum까지  -->
					<li class="page-item ${ pageNum eq i ? 'active' : '' }"><a
						class="page-link" href="list?pageNum=${i }&condition=${condition}&keyword=${encodedK}">${i }</a></li>
				</c:forEach>
				<%--
               마지막 페이지 번호가 전체 페이지의 갯수보다 작으면 Next 링크를 제공한다. 
             --%>

				<c:if test="${endPageNum lt totalPageCount}">
					<li class="page-item"><a class="page-link"
						href="list?pageNum=${endPageNum+1}&condition=${condition}&keyword=${encodedK}">Next</a></li>
				</c:if>
			</ul>
		</nav>
		
		
		<!-- 검색 form -->
		<form action="list" method="get">
			<label for="condition">검색조건</label>
			<select name="condition" id="condition">
				<option value="title_Content" ${condition eq 'title_Content' ? 'selected':'' }>제목 + 내용</option>
				<option value="title" ${condition eq 'title' ? 'selected':'' }>제목</option>
				<option value="writer" ${condition eq 'writer' ? 'selected':'' }>작성자</option>
			</select>
			<input type="text" name="keyword" placeholder="검색어.." value="${keyword}"/>
			<button type="submit">검색</button>
		</form>
		<c:if test="${not empty condition}">
			<p>
				<strong>${totalRow}</strong>개의 자료가 검색되었습니다.
				<a href="list">리셋</a>
			</p>
		</c:if>
		<div>
			<a href="${pageContext.request.contextPath}/">home</a>
		</div>
	</div>
	
	
	<script>
		function deleteConfirm(num) {
			let isDelete = confirm("삭제 하시겠습니까?");
			if (isDelete) {
				location.href = "delete?num=" + num;
			}
		}
	</script>
</body>
</html>