<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/detail.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>

<style>
   .content{
      border: 1px dotted gray;
   }
   
   /* 댓글 프로필 이미지를 작은 원형으로 만든다. */
   .profile-image{
      width: 50px;
      height: 50px;
      border: 1px solid #cecece;
      border-radius: 50%;
   }
   /* ul 요소의 기본 스타일 제거 */
   .comments ul{
      padding: 0;
      margin: 0;
      list-style-type: none;
   }
   .comments dt{
      margin-top: 5px;
   }
   .comments dd{
      margin-left: 50px;
   }
   .comment-form textarea, .comment-form button{
      float: left;
   }
   .comments li{
      clear: left;
   }
   .comments ul li{
      border-top: 1px solid #888;
   }
   .comment-form textarea{
      width: 84%;
      height: 100px;
   }
   .comment-form button{
      width: 14%;
      height: 100px;
   }
   /* 댓글에 댓글을 다는 폼과 수정폼은 일단 숨긴다. */
   .comments .comment-form{
      display: none;
   }
   /* .reply_icon 을 li 요소를 기준으로 배치 하기 */
   .comments li{
      position: relative;
   }
   .comments .reply-icon{
      position: absolute;
      top: 1em;
      left: 1em;
      color: red;
   }
   pre {
     display: block;
     padding: 9.5px;
     margin: 0 0 10px;
     font-size: 13px;
     line-height: 1.42857143;
     color: #333333;
     word-break: break-all;
     word-wrap: break-word;
     background-color: #f5f5f5;
     border: 1px solid #ccc;
     border-radius: 4px;
   }   
   
   .loader{
      /* 로딩 이미지를 가운데 정렬하기 위해 */
      text-align: center;
      /* 일단 숨겨 놓기 */
      display: none;
   }   
   
   .loader svg{
      animation: rotateAni 1s ease-out infinite;
   }
   
   @keyframes rotateAni{
      0%{
         transform: rotate(0deg);
      }
      100%{
         transform: rotate(360deg);
      }
   }
</style>
</head>
<body>
	<div class="container">

		<%--만일 이전글( 더 예전글)의 글번호가 0이 아니라면(이전글이 존재한다면 --%>
		<c:if test="${dto.prevNum ne 0 }">
			<a
				href="detail?num=${dto.prevNum}&condition=${condition}&keyword=${encodedK}">이전글
			</a>
		</c:if>

		<%--만일 다음글(더 최신글)의 글번호가 0이 아니라면(다음글이 존재한다면) --%>
		<c:if test="${dto.nextNum ne 0 }">
			<a
				href="detail?num=${dto.nextNum}&condition=${condition}&keyword=${encodedK}">다음글</a>
		</c:if>

		<%-- 만약 검색 키워드가 있다면  --%>
		<c:if test="${not empty keyword }">
			<p>
				<strong>${condition }</strong> 조건 <strong>${keyword }</strong> 검색어로
				검색된 내용 자세히 보기
			</p>
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
		<h4>댓글을 입력해주세요</h4>
		<!-- 원글에 댓글을 작성할 폼 -->
		<form class="comment-form insert-form" action="comment_insert"
			method="post">
			<!-- 원글의 글번호가 댓글의 ref_group 번호가 된다. -->
			<input type="hidden" name="ref_group" value="${dto.num }" />
			<!-- 원글의 작성자가 댓글의 대상자가 된다. -->
			<input type="hidden" name="target_id" value="${dto.writer }" />

			<textarea name="content">${empty id ? '댓글 작성을 위해 로그인이 필요 합니다.' : '' }</textarea>
			<button type="submit">등록</button>
		</form>
		<!-- 댓글 목록 -->
		<div class="comments">
			<a href="newComment">최신댓글 순 </a>
			<a href="oldComment">오래된댓글 순</a>
			<ul>
				<c:forEach var="tmp" items="${commentList }">
					<c:choose>
						<%-- 삭제가 되었다면 ' 삭제된 댓글입니다 ' 노출--%>
						<c:when test="${tmp.deleted eq 'yes' }">
							<li>삭제된 댓글 입니다.</li>
						</c:when>
						
						
						<%-- 삭제가 되지 않았다면 아래 노출 --%>
						<c:otherwise>
							<%-- 만약 댓글 번호와 댓글의 그룹 번호가 같다면,  대댓글이 아니라 원글 이라는 것  --%>
							<c:if test="${tmp.num eq tmp.comment_group }">
								<li id="reli${tmp.num }">
							</c:if>
							<%--  만약 댓글 번호와 댓글의 그룹 번호가 다르다면,  대댓글이라는 것 --%>
							<c:if test="${tmp.num ne tmp.comment_group }">
								<li id="reli${tmp.num }" style="padding-left: 50px;"><svg
										class="reply-icon" xmlns="http://www.w3.org/2000/svg"
										width="16" height="16" fill="currentColor"
										class="bi bi-arrow-return-right" viewBox="0 0 16 16">
                             <path fill-rule="evenodd"
											d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z" />
                        </svg>
							</c:if>
							<dl>
								<dt>
									<%-- 만약 profile이 비어있다면(사진이 없다면) --%>
									<c:if test="${ empty tmp.profile }">
										<svg class="profile-image" xmlns="http://www.w3.org/2000/svg"
											width="16" height="16" fill="currentColor"
													class="bi bi-person-circle" viewBox="0 0 16 16">
		                                   <path
														d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
		                                   <path fill-rule="evenodd"
														d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
		                                </svg>
									</c:if>
									<%-- 만약 profile이 비어있지 않다면(사진이 있다면) --%>
									<c:if test="${not empty tmp.profile }">
										<img class="profile-image"
											src="${pageContext.request.contextPath}/users/images/${tmp.profile }" />
									</c:if>
									<span>${tmp.writer }</span>
									<%-- 댓글 번호와  comment_group 번호가 다르면 -> 대댓글이 있다면.. --%>
									<c:if test="${tmp.num ne tmp.comment_group }">
                                		 <span style="background-color:#EBF4F4">@<i>${tmp.target_id } </i></span>
									</c:if>
									<span>${tmp.regdate }</span>
									<a data-num="${tmp.num }"
										href="javascript:" class="reply-link">답글</a>
									<%--  --%>
									<c:if test="${ (id ne null) and (tmp.writer eq id) }">
										<a data-num="${tmp.num }" class="update-link"
											href="javascript:">수정</a>
										<a data-num="${tmp.num }" class="delete-link"
											href="javascript:">삭제</a>
									</c:if>
								</dt>
								<dd>
									<pre id="pre${tmp.num }">${tmp.content }</pre>
								</dd>
							</dl>
							<form id="reForm${tmp.num }"
								class="animate__animated comment-form re-insert-form"
								action="comment_insert" method="post">
								<input type="hidden" name="ref_group" value="${dto.num }" /> <input
									type="hidden" name="target_id" value="${tmp.writer }" /> <input
									type="hidden" name="comment_group"
									value="${tmp.comment_group }" />
								<textarea name="content"></textarea>
								<button type="submit">등록</button>
							</form>
							<c:if test="${tmp.writer eq id }">
								<form id="updateForm${tmp.num }"
									class="comment-form update-form" action="comment_update"
									method="post">
									<input type="hidden" name="num" value="${tmp.num }" />
									<textarea name="content">${tmp.content }</textarea>
									<button type="submit">수정</button>
								</form>
							</c:if>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
		<!-- 댓글 로딩 이미지(평소엔 안보이게 해두기)  -->
		<div class="loader">
			<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100"
				fill="currentColor" class="bi bi-arrow-clockwise"
				viewBox="0 0 16 16">
	           <path fill-rule="evenodd"
						d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z" />
	           <path
						d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z" />
      		</svg>
		</div>

		
	</div>
	<script
		src="${pageContext.request.contextPath}/js/gura_util.js"></script>
	<script>
	   
	   //클라이언트가 로그인 했는지 여부
	   //js에서 확인 할 수 있도록  미리 출력해놓기 
	   let isLogin=${ not empty id }; //id가 비지 않았으면 true (로그인함)  / id가 비었으면 false(로그인안됨)
	   
	   document.querySelector(".insert-form")
	      .addEventListener("submit", function(e){
	         //만일 로그인 하지 않았으면 
	         if(!isLogin){
	            //폼 전송을 막고 
	            e.preventDefault();
	            //로그인 폼으로 이동 시킨다.
	            location.href=
	               "${pageContext.request.contextPath}/users/loginform?url=${pageContext.request.contextPath}/cafe/detail?num=${dto.num}";
	         }
	      });
	   
	   /*
	      detail
	 페이지 로딩 시점에 만들어진 1 페이지에 해당하는 
	      댓글에 이벤트 리스너 등록 하기 
	   */
	   addUpdateFormListener(".update-form");
	   addUpdateListener(".update-link");
	   addDeleteListener(".delete-link");
	   addReplyListener(".reply-link");
	   
	   
	   //댓글의 현재 페이지 번호를 관리할 변수를 만들고 초기값 1 대입하기
	   let currentPage=1;
	   //마지막 페이지는 totalPageCount 이다.  
	   <%-- 댓글의 갯수가 0일 때(totalPageCount가 0일때) 오류를 방지하기 위해  --%>
	   let lastPage=${totalPageCount eq 0 ? 1 : totalPageCount};
	   
	   //추가로 댓글을 요청하고 그 작업이 끝났는지 여부를 관리할 변수 
	   let isLoading=false; //현재 로딩중인지 여부 
	   
	   /*
	      window.scrollY => 위쪽으로 스크롤된 길이
	      window.innerHeight => 웹브라우저의 창의 높이
	      document.body.offsetHeight => body 의 높이 (문서객체가 차지하는 높이)
	   */
	   window.addEventListener("scroll", function(){
	      //바닥 까지 스크롤 했는지 여부 
	      const isBottom = 
	         window.innerHeight + window.scrollY  >= document.body.offsetHeight;
	      //현재 페이지가 마지막 페이지인지 여부 알아내기
	      let isLast = currentPage == lastPage;   
	      //현재 바닥까지 스크롤 했고 로딩중이 아니고 현재 페이지가 마지막이 아니라면
	      if(isBottom && !isLoading && !isLast){
	         //로딩바 띄우기
	         document.querySelector(".loader").style.display="block";
	         
	         //로딩 작업중이라고 표시
	         isLoading=true;
	         
	         //현재 댓글 페이지를 1 증가 시키고 
	         currentPage++;
	         
	         /*
	            해당 페이지의 내용을 ajax 요청을 통해서 받아온다.
	            "pageNum=xxx&num=xxx" 형식으로 GET 방식 파라미터를 전달한다. 
	         */
	         ajaxPromise("ajax_comment_list","get",
	               "pageNum="+currentPage+"&num=${dto.num}")
	         .then(function(response){
	            //json 이 아닌 html 문자열을 응답받았기 때문에  return response.text() 해준다.
	            return response.text();
	         })
	         .then(function(data){
	            //data 는 html 형식의 문자열이다. 
	            console.log(data);
	            // beforebegin | afterbegin | beforeend | afterend
	            document.querySelector(".comments ul")
	               .insertAdjacentHTML("beforeend", data);
	            //로딩이 끝났다고 표시한다.
	            isLoading=false;
	            //새로 추가된 댓글 li 요소 안에 있는 a 요소를 찾아서 이벤트 리스너 등록 하기 
	            // .page-"+currentPage+"를 추가하는 이유 : 기존의 a요소들에게 이벤트 리스너가 다시 걸리면 안되기 때문에
	            addUpdateListener(".page-"+currentPage+" .update-link");
	            addDeleteListener(".page-"+currentPage+" .delete-link");
	            addReplyListener(".page-"+currentPage+" .reply-link");
	            //새로 추가된 댓글 li 요소 안에 있는 댓글 수정폼에 이벤트 리스너 등록하기
	            addUpdateFormListener(".page-"+currentPage+" .update-form");
	            
	            //로딩바 숨기기
	            document.querySelector(".loader").style.display="none";
	         });
	      }
	   });
	   
	   //인자로 전달되는 선택자를 이용해서 이벤트 리스너를 등록하는 함수 
	   function addUpdateListener(sel){
	      //댓글 수정 링크의 참조값을 배열에 담아오기 
	      // sel 은  ".page-xxx  .update-link" 형식의 내용이다 
	      let updateLinks=document.querySelectorAll(sel);
	      for(let i=0; i<updateLinks.length; i++){
	         updateLinks[i].addEventListener("click", function(){
	            //click 이벤트가 일어난 바로 그 요소의 data-num 속성의 value 값을 읽어온다. 
	            const num=this.getAttribute("data-num"); //댓글의 글번호
	            document.querySelector("#updateForm"+num).style.display="block";
	            
	         });
	      }
	   }
	   function addDeleteListener(sel){
	      //댓글 삭제 링크의 참조값을 배열에 담아오기 
	      let deleteLinks=document.querySelectorAll(sel);
	      for(let i=0; i<deleteLinks.length; i++){
	         deleteLinks[i].addEventListener("click", function(){
	            //click 이벤트가 일어난 바로 그 요소의 data-num 속성의 value 값을 읽어온다. 
	            const num=this.getAttribute("data-num"); //댓글의 글번호
	            const isDelete=confirm("댓글을 삭제 하시겠습니까?");
	            if(isDelete){
	               // gura_util.js 에 있는 함수들 이용해서 ajax 요청
	               ajaxPromise("comment_delete", "post", "num="+num)
	               .then(function(response){
	                  return response.json();
	               })
	               .then(function(data){
	                  //만일 삭제 성공이면 
	                  if(data.isSuccess){
	                     //댓글이 있는 곳에 삭제된 댓글입니다를 출력해 준다. 
	                     document.querySelector("#reli"+num).innerText="삭제된 댓글입니다.";
	                  }
	               });
	            }
	         });
	      }
	   }
	   function addReplyListener(sel){
	      //댓글 링크의 참조값을 배열에 담아오기 
	      let replyLinks=document.querySelectorAll(sel);
	      //반복문 돌면서 모든 링크에 이벤트 리스너 함수 등록하기
	      for(let i=0; i<replyLinks.length; i++){
	         replyLinks[i].addEventListener("click", function(){
	            
	            if(!isLogin){
	               const isMove=confirm("로그인이 필요 합니다. 로그인 페이지로 이동 하시겠습니까?");
	               if(isMove){
	                  location.href=
	                     "${pageContext.request.contextPath}/users/loginform?url=${pageContext.request.contextPath}/cafe/detail?num=${dto.num}";
	               }
	               return;
	            }
	            
	            //click 이벤트가 일어난 바로 그 요소의 data-num 속성의 value 값을 읽어온다. 
	            const num=this.getAttribute("data-num"); //댓글의 글번호
	            
	            const form=document.querySelector("#reForm"+num);
	            
	            //현재 문자열을 읽어온다 ( "답글" or "취소" )
	            let current = this.innerText;
	            
	            if(current == "답글"){
	               //번호를 이용해서 댓글의 댓글폼을 선택해서 보이게 한다. 
	               form.style.display="block";
	               form.classList.add("animate__rubberBand");
	               this.innerText="취소";   
	               form.addEventListener("animationend", function(){ //애니메이션을 끝났을 때, 'animationend'라는 이벤트 발생
	                  form.classList.remove("animate__rubberBand"); // 그때, 효과를 삭제 해야 다음에도 동작한다 
	               }, {once:true});
	            }else if(current == "취소"){
	               form.classList.add("animate__fadeOut");
	               this.innerText="답글";
	               form.addEventListener("animationend", function(){
	                  form.classList.remove("animate__fadeOut");
	                  form.style.display="none";
	               },{once:true});
	            }
	         });
	      }
	   }
	   
	   function addUpdateFormListener(sel){
	      //댓글 수정 폼의 참조값을 배열에 담아오기
	      let updateForms=document.querySelectorAll(sel);
	      for(let i=0; i<updateForms.length; i++){
	         //폼에 submit 이벤트가 일어 났을때 호출되는 함수 등록 
	         updateForms[i].addEventListener("submit", function(e){
	            //submit 이벤트가 일어난 form 의 참조값을 form 이라는 변수에 담기 
	            const form=this;
	            //폼 제출을 막은 다음 
	            e.preventDefault();
	            //이벤트가 일어난 폼을 ajax 전송하도록 한다.
	            ajaxFormPromise(form)
	            .then(function(response){
	               return response.json();
	            })
	            .then(function(data){
	               if(data.isSuccess){
	                  /*
	                     document.querySelector() 는 html 문서 전체에서 특정 요소의 
	                     참조값을 찾는 기능
	                     
	                     특정문서의 참조값.querySelector() 는 해당 문서 객체의 자손 요소 중에서
	                     특정 요소의 참조값을 찾는 기능
	                  */
	                  const num=form.querySelector("input[name=num]").value;
	                  const content=form.querySelector("textarea[name=content]").value;
	                  //수정폼에 입력한 value 값을 pre 요소에도 출력하기 
	                  document.querySelector("#pre"+num).innerText=content;
	                  form.style.display="none";
	               }
	            });
	         });
	      }
	   }
	</script>




	
</body>
</html>