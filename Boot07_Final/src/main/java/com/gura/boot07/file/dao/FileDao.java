package com.gura.boot07.file.dao;

import java.util.List;

import com.gura.boot07.file.dto.FileDto;

public interface FileDao {
   public void insert(FileDto dto);
   public FileDto getData(int num);
   public void delete(int num);
   public List<FileDto> getList(FileDto dto);
   
   //전체 자료의 갯수(페이징 처리를 하기위해 필요하다) 
   //dto를 전달하는 이유 : 검색 키워드에 맞는 자료가 몇개인지 찾기위해 
   public int getCount(FileDto dto);
}