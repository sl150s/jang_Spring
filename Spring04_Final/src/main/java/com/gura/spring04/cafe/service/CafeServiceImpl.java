package com.gura.spring04.cafe.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gura.spring04.cafe.dao.CafeDao;
import com.gura.spring04.cafe.dto.CafeDto;
import com.gura.spring04.file.dto.FileDto;

@Service
public class CafeServiceImpl implements CafeService{
	
	@Autowired
	private CafeDao cafeDao;

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
		
		
	 /* ===============================================
        [ 검색 키워드에 관련된 처리 ]
        -검색 키워드가 파라미터로 넘어올수도 있고 안넘어 올수도 있다.      
     */
     String keyword=request.getParameter("keyword");
     String condition=request.getParameter("condition");
     //만일 키워드가 넘어오지 않는다면 
     if(keyword==null){
        //키워드와 검색 조건에 빈 문자열을 넣어준다. 
        //클라이언트 웹브라우저에 출력할때 "null" 을 출력되지 않게 하기 위해서  
        keyword="";
        condition=""; 
     }

     //특수기호를 인코딩한 키워드를 미리 준비한다. 
     String encodedK=URLEncoder.encode(keyword);
        
     //FileDto 객체에 startRowNum 과 endRowNum 을 담는다.
     CafeDto dto=new CafeDto();
     dto.setStartRowNum(startRowNum);
     dto.setEndRowNum(endRowNum);

     //만일 검색 키워드가 넘어온다면 
     if(!keyword.equals("")){
        //검색 조건이 무엇이냐에 따라 분기 하기
        if(condition.equals("title_Content")){//제목 + 내용 검색인 경우
           dto.setTitle(keyword);
           dto.setContent(keyword);
        }else if(condition.equals("title")){ //제목 검색인 경우
           dto.setTitle(keyword);
        }else if(condition.equals("writer")){ //작성자 검색인 경우
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
		request.setAttribute("totalRow", totalRow); //전체 글의 갯수 
		request.setAttribute("keyword", keyword); //키워드
		request.setAttribute("condition", condition); //검색조건 
		
	}

	@Override
	public void getDetail(HttpServletRequest request) {
		//자세히 보여줄 글 번호를 읽어온다. 
		int num = Integer.parseInt(request.getParameter("num"));
		//조회수 올리기 
		cafeDao.addViewCount(num);
		
		//해당 번호의 컨텐츠 내용 가져오기
		CafeDto dto = cafeDao.getData(num);
		
		//request scope에 글 하나의 정보를 담기
		request.setAttribute("dto", dto);
	}

	@Override
	public void saveContent(CafeDto dto) {
		cafeDao.insert(dto);
	}

	@Override
	public void updateContent(CafeDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteContent(int num, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

}
