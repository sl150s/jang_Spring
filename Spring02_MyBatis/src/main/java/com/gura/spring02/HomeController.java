package com.gura.spring02;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
   
   @RequestMapping("/")
   public String home(HttpServletRequest request) {
	   //응답에 필요한 데이터(Model)라고 가정하자 
      List<String> noticeList = new ArrayList<String>();
      noticeList.add("날씨가 많이 추워졌네요. ");
      noticeList.add("블라");
      noticeList.add("저쩌구 저ㅉ구 ");
      
      //Model을 request scope에 특정 key 값에 담는다 
      request.setAttribute("noticeList", noticeList);
	   
      // /WEB-INF/views/home.jsp 페이지로 응답을 위임시키기(forward 이동)
      return "home";
   }
   
}