<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/insertForm.jsp</title>
<style>
/* 이미지 업로드 폼을 숨긴다 */

</style>
</head>
<body>
	<div class="container">
		<h3>사진 업로드</h3>
		<!-- 
         파일 업로드 폼 작성법
         1. method="post"
         2. enctype="multipart/form-data"
         3. <input type="file" />
         - enctype="multipart/form-data" 가 설정된 폼을 전송하면
           폼전송된 내용을 추출할때 HttpServletRequest 객체로 추출을 할수 없다
          MultipartRequest 객체를 이용해서 추출해야 한다. 
       -->
       
       <form action="insert" method="post" enctype="multipart/form-data">
			<input type="hidden" name="writer" value="${id}" />
			<div>
				<!-- <a id="galleryLink" href="javascript:"> 
					<span>사진 새로 등록하기</span>
					<img id="galleryImage"
						src="${pageContext.request.contextPath}${dto.imagePath}">
				</a> -->
				<div>
					<label for="imagePath">사진파일</label> 
					<input type="file" name="imagePath"
						id="imagePath" accept=".jpg, .png, .gif" />
				</div>
			</div>
			<div>
				<textarea name="caption" cols="30" rows="10" placeholder="사진 설명을 써주세요."></textarea>
			</div>
			<button type="submit">업로드</button>
		</form>
		
		
		<!-- <form action="insert" method="post" enctype="multipart/form-data">
			<input type="hidden" name="writer" value="${id}" />
			<div>
				<a id="galleryLink" href="javascript:"> 
					<span>사진 새로 등록하기</span>
					<img id="galleryImage"
						src="${pageContext.request.contextPath}${dto.imagePath}">
				</a>
			</div>
			<div>
				<textarea name="caption" cols="30" rows="10" placeholder="사진 설명을 써주세요."></textarea>
			</div>
			<button type="submit">업로드</button>
		</form>
		
		<form id="imageForm" action="${pageContext.request.contextPath}/gallery/galley_upload" method="post"
			enctype="multipart/form-data">
			프로필 사진 <input type="file" id="image" name="image"
				accept=".jpg, .png, .gif" />
			<button type="submit">업로드</button>
		</form> -->
			
		
		<!-- gura_util.js 로딩 -->
	<!--<script src="${pageContext.request.contextPath }/resources/js/gura_util.js"></script>
	<script>
		//프로필 이미지 링크를 클릭하면
		document.querySelector("#galleryLink").addEventListener("click", function () {
		  // input type="file" 을 강제 클릭 시킨다.
		  document.querySelector("#image").click();
		});
	
		//프로필 이미지를 선택하면(바뀌면) 실행할 함수 등록
		document.querySelector("#image").addEventListener("change", function () {
		  //ajax 전송할 폼의 참조값 얻어오기
		  const form = document.querySelector("#imageForm");
		  //gura_util.js 에 있는 함수를 이용해서 ajax 전송하기
		  ajaxFormPromise(form)
		    .then(function (response) {
		      return response.json();
		    })
		    .then(function (data) {
		      console.log(data);
		      // input name="imagePath" 요소의 value 값으로 이미지 경로 넣어주기
		      document.querySelector("input[name=imagePath]").value = data.imagePath;
	
		      // img 요소를 문자열로 작성한 다음
		      let img = `<img id="galleryImage" 
		               src="${pageContext.request.contextPath}\${data.imagePath}">`;
		      //id 가 profileLink 인 요소의 내부(자식요소)에 덮어쓰기 하면서 html 형식으로 해석해 주세요 라는 의미
		      document.querySelector("#galleryLink").innerHTML = img;
		    });
		});

	</script>  -->
	</div>
</body>
</html>