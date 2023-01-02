package com.gura.boot07.cafe.service;

import javax.servlet.http.HttpServletRequest;

import com.gura.boot07.cafe.dto.CafeCommentDto;
import com.gura.boot07.cafe.dto.CafeDto;

public interface CafeService {
   public void getList(HttpServletRequest request); //글 목록 불러오기 
   public void getDetail(HttpServletRequest request);
   public void saveContent(CafeDto dto); // 글 저장하기
   public void updateContent(CafeDto dto);
   public void deleteContent(int num, HttpServletRequest request);
   public void getData(HttpServletRequest request); //글 수정하기 위해 정보 불러오는 기능
   
   public void saveComment(HttpServletRequest request); //댓글 저장
   public void deleteComment(HttpServletRequest request); //댓글 삭제
   public void updateComment(CafeCommentDto dto); //댓글 수정
   public void moreCommentList(HttpServletRequest request); //댓글 더보기 기능
}