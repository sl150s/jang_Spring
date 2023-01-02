package com.gura.boot07;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/*
 *  [ jsp 페이지를 사용하기 위한 설정 ]
 *  
 *  1. pom.xml  에   tomcat-embed-jasper 와 jstl  dependency 를 추가한다.
 *  2. /webapp/WEB-INF/views/  구조로 폴더를 src/main 하위에 만든다.
 *  3. view page 의  prefix 와  suffix 설정을 추가한다. 
 *     만일 application.properties 에 설정을 할거라면 
 *     
 *     spring.mvc.view.prefix=/WEB-INF/views/
      spring.mvc.view.suffix=.jsp
      
      이 내용을 추가한다( 추후에 설정을 다른곳에서 해야함 )
 */
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HomeContoller {
   
   @GetMapping("/")
   public String home(HttpServletRequest request) {
      
      List<String> noticeList=new ArrayList<>();
      noticeList.add("Spring Boot 시작 입니다.");
      noticeList.add("어쩌구...");
      noticeList.add("저쩌구...");
      
      request.setAttribute("noticeList", noticeList);
      
      return "home";
   }
   
 
}























