package com.gura.boot07.shop.dto;

import org.apache.ibatis.type.Alias;

@Alias("orderDto")
public class OrderDto {
   private int num;//주문 번호
   private String id; //주문자의 아이디
   private int code; //상품번호
   private String addr; //배송 주소
   
   public OrderDto() {}
   
   public OrderDto(int num, String id, int code, String addr) {
      super();
      this.num = num;
      this.id = id;
      this.code = code;
      this.addr = addr;
   }

   public int getNum() {
      return num;
   }

   public void setNum(int num) {
      this.num = num;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public int getCode() {
      return code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public String getAddr() {
      return addr;
   }

   public void setAddr(String addr) {
      this.addr = addr;
   }   
}