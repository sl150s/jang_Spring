package com.gura.spring04.cafe.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gura.spring04.cafe.dao.CafeCommentDao;
import com.gura.spring04.cafe.dao.CafeDao;
import com.gura.spring04.cafe.dto.CafeCommentDto;
import com.gura.spring04.cafe.dto.CafeDto;
import com.gura.spring04.exception.NotDeleteException;
import com.gura.spring04.file.dto.FileDto;

@Service
public class CafeServiceImpl implements CafeService {

	// 하나의 서비스에서 여러 개의 dao에 의존할 수도 있다.
	@Autowired
	private CafeDao cafeDao;
	@Autowired
	private CafeCommentDao cafeCommentDao;

	public void getList(HttpServletRequest request) {
		// 한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT = 5;
		// 하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT = 5;

		// 보여줄 페이지의 번호를 일단 1이라고 초기값 지정
		int pageNum = 1;

		// 페이지 번호가 파라미터로 전달되는지 읽어와 본다.
		String strPageNum = request.getParameter("pageNum");
		// 만일 페이지 번호가 파라미터로 넘어 온다면
		if (strPageNum != null) {
			// 숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
			pageNum = Integer.parseInt(strPageNum);
		}

		// 보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		/*
		 * =============================================== [ 검색 키워드에 관련된 처리 ] -검색 키워드가
		 * 파라미터로 넘어올수도 있고 안넘어 올수도 있다.
		 */
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		// 만일 키워드가 넘어오지 않는다면
		if (keyword == null) {
			// 키워드와 검색 조건에 빈 문자열을 넣어준다.
			// 클라이언트 웹브라우저에 출력할때 "null" 을 출력되지 않게 하기 위해서
			keyword = "";
			condition = "";
		}

		// 특수기호를 인코딩한 키워드를 미리 준비한다.
		String encodedK = URLEncoder.encode(keyword);

		// FileDto 객체에 startRowNum 과 endRowNum 을 담는다.
		CafeDto dto = new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		// 만일 검색 키워드가 넘어온다면
		if (!keyword.equals("")) {
			// 검색 조건이 무엇이냐에 따라 분기 하기
			if (condition.equals("title_Content")) {// 제목 + 내용 검색인 경우
				dto.setTitle(keyword);
				dto.setContent(keyword);
			} else if (condition.equals("title")) { // 제목 검색인 경우
				dto.setTitle(keyword);
			} else if (condition.equals("writer")) { // 작성자 검색인 경우
				dto.setWriter(keyword);
			} // 다른 검색 조건을 추가 하고 싶다면 아래에 else if() 를 계속 추가 하면 된다.
		}
		// ===============================================

		// 파일 목록을 select 해 온다.(검색 키워드가 있는 경우 키워드에 부합하는 전체 글)
		List<CafeDto> list = cafeDao.getList(dto);

		// 전체 글의 갯수
		int totalRow = cafeDao.getCount(dto);
		// 하단 시작 페이지 번호
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		// 하단 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		// 전체 페이지의 갯수 구하기
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		// 끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if (endPageNum > totalPageCount) {
			endPageNum = totalPageCount; // 보정해 준다.
		}

		// 응답에 필요한 데이터를 view page 에 전달하기 위해 request scope 에 담는다
		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("encodedK", encodedK);
		request.setAttribute("totalRow", totalRow); // 전체 글의 갯수
		request.setAttribute("keyword", keyword); // 키워드
		request.setAttribute("condition", condition); // 검색조건

	}

	@Override
	public void getDetail(HttpServletRequest request) {
		// 자세히 보여줄 글 번호를 읽어온다.
		int num = Integer.parseInt(request.getParameter("num"));
		// 조회수 올리기
		cafeDao.addViewCount(num);

		/*
		 * =============================================== [ 검색 키워드에 관련된 처리 ] -검색 키워드가
		 * 파라미터로 넘어올수도 있고 안넘어 올수도 있다.
		 */
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		// 만일 키워드가 넘어오지 않는다면
		if (keyword == null) {
			// 키워드와 검색 조건에 빈 문자열을 넣어준다.
			// 클라이언트 웹브라우저에 출력할때 "null" 을 출력되지 않게 하기 위해서
			keyword = "";
			condition = "";
		}
		// 특수기호를 인코딩한 키워드를 미리 준비한다.
		String encodedK = URLEncoder.encode(keyword);

		
		
		
		/*
		 * [ 댓글 페이징 처리에 관련된 로직 ]
		 */
		// 한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT = 10;

		// detail.jsp 페이지에서는 항상 1페이지의 댓글 내용만 출력한다.
		int pageNum = 1;

		// 보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		// 원글의 글번호를 이용해서 해당글에 달린 댓글 목록을 얻어온다.
		CafeCommentDto commentDto = new CafeCommentDto();
		commentDto.setRef_group(num);
		// 1페이지에 해당하는 startRowNum 과 endRowNum 을 dto 에 담아서
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);

		// 1페이지에 해당하는 댓글 목록만 select 되도록 한다.
		List<CafeCommentDto> commentList = cafeCommentDao.getList(commentDto);

		// 원글의 글번호를 이용해서 댓글 전체의 갯수를 얻어낸다.
		int totalRow = cafeCommentDao.getCount(num);
		// 댓글 전체 페이지의 갯수 -> 필요한 이유 : 댓글 전체 페이지 갯수를 알고 있어야 몇페이지까지 ajax 요청을 해야하는지 미리 설정할수있다
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);

		// CafetDto 객체를 생성해서
		CafeDto dto = new CafeDto();
		// 자세히 보여줄 글 번호를 넣어준다.
		dto.setNum(num);

		// 만일 검색 키워드가 넘어온다면
		if (!keyword.equals("")) {
			// 검색 조건이 무엇이냐에 따라 분기 하기
			if (condition.equals("title_Content")) {// 제목 + 내용 검색인 경우
				dto.setTitle(keyword);
				dto.setContent(keyword);
			} else if (condition.equals("title")) { // 제목 검색인 경우
				dto.setTitle(keyword);
			} else if (condition.equals("writer")) { // 작성자 검색인 경우
				dto.setWriter(keyword);
			} // 다른 검색 조건을 추가 하고 싶다면 아래에 else if() 를 계속 추가 하면 된다.
		}

		// 해당 번호의 컨텐츠 내용 가져오기
		CafeDto resultDto = cafeDao.getData(dto);

		// request scope에 글 하나의 정보를 담기
		request.setAttribute("dto", resultDto);
		request.setAttribute("encodedK", encodedK); // 인코딩된 키워드
		request.setAttribute("keyword", keyword); // 키워드
		request.setAttribute("condition", condition); // 검색조건
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("commentList", commentList);
	}

	@Override
	public void saveContent(CafeDto dto) {
		cafeDao.insert(dto);
	}

	@Override
	public void updateContent(CafeDto dto) {
		cafeDao.update(dto);
	}

	@Override
	public void deleteContent(int num, HttpServletRequest request) {
		// request는 언제 쓰는냐 : 다른 사람이 글 삭제하는걸 방지
		// 세션에서 로그인된 아이디를 읽어와서

		// 글 작성자와 로그인된 아이디가 다르다면

		// 예외를 발생 시켜서 삭제가 안되도록 한다.

		cafeDao.delete(num);
	}

	@Override
	public void getData(HttpServletRequest request) {
		// 수정할 글 번호
		int num = Integer.parseInt(request.getParameter("num"));

		// 수정할 글의 정보를 얻어와서
		CafeDto dto = cafeDao.getData(num);

		// request에 담아준다
		request.setAttribute("dto", dto);
	}

	@Override
	public void saveComment(HttpServletRequest request) {
		// 폼 전송되는 파라미터 추출
		int ref_group = Integer.parseInt(request.getParameter("ref_group"));// 원글의 글번호
		String target_id = request.getParameter("target_id"); // 댓글 대상자의 아이디
		String content = request.getParameter("content"); // 댓글의 내용
		/*
		 * 원글의 댓글은 comment_group 번호가 전송이 안되고 댓글의 댓글은 comment_group 번호가 전송이 된다. 따라서 null
		 * 여부를 조사하면 원글의 댓글인지 댓글의 댓글인지 판단할수 있다.
		 */
		String comment_group = request.getParameter("comment_group");

		// 댓글 작성자는 session 영역에서 얻어내기
		String writer = (String) request.getSession().getAttribute("id");
		// 댓글의 시퀀스 번호 미리 얻어내기
		int seq = cafeCommentDao.getSequence(); // 미리 얻어내지 않으면 dto
		// 저장할 댓글의 정보를 dto 에 담기
		CafeCommentDto dto = new CafeCommentDto();
		dto.setNum(seq);
		dto.setWriter(writer);
		dto.setTarget_id(target_id);
		dto.setContent(content);
		dto.setRef_group(ref_group);
		// 원글의 댓글인경우
		if (comment_group == null) {
			// 댓글의 글번호를 comment_group 번호로 사용한다.
			dto.setComment_group(seq);
		} else {
			// 전송된 comment_group 번호를 숫자로 바꾸서 dto 에 넣어준다.
			dto.setComment_group(Integer.parseInt(comment_group));
		}
		// 댓글 정보를 DB 에 저장하기
		cafeCommentDao.insert(dto);
	}

	@Override
	public void deleteComment(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		// 삭제할 댓글 정보를 읽어와서
		CafeCommentDto dto = cafeCommentDao.getData(num);
		String id = (String) request.getSession().getAttribute("id");
		// 글 작성자와 로그인된 아이디와 일치하지 않으면
		if (!dto.getWriter().equals(id)) {
			throw new NotDeleteException("남의 댓글 지우면 혼난당!");
		}

		cafeCommentDao.delete(num);

	}

	@Override
	public void updateComment(CafeCommentDto dto) {
		cafeCommentDao.update(dto);
	}

	@Override
	public void moreCommentList(HttpServletRequest request) {
		// 로그인된 아이디
		String id = (String) request.getSession().getAttribute("id");
		// ajax 요청 파라미터로 넘어오는 댓글의 페이지 번호를 읽어낸다
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		// ajax 요청 파라미터로 넘어오는 원글의 글 번호를 읽어낸다
		int num = Integer.parseInt(request.getParameter("num"));
		/*
		 * [ 댓글 페이징 처리에 관련된 로직 ]
		 */
		// 한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT = 10;

		// 보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		// 원글의 글번호를 이용해서 해당글에 달린 댓글 목록을 얻어온다.
		CafeCommentDto commentDto = new CafeCommentDto();
		commentDto.setRef_group(num);
		// 1페이지에 해당하는 startRowNum 과 endRowNum 을 dto 에 담아서
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);

		// pageNum에 해당하는 댓글 목록만 select 되도록 한다.
		List<CafeCommentDto> commentList = cafeCommentDao.getList(commentDto);
		// 원글의 글번호를 이용해서 댓글 전체의 갯수를 얻어낸다.
		int totalRow = cafeCommentDao.getCount(num);
		// 댓글 전체 페이지의 갯수
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);

		// view page 에 필요한 값 request 에 담아주기
		request.setAttribute("commentList", commentList);
		request.setAttribute("num", num); // 원글의 글번호
		request.setAttribute("pageNum", pageNum); // 댓글의 페이지 번호
	}

}
