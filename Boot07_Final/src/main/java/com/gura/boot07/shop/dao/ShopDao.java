package com.gura.boot07.shop.dao;

import java.util.List;

import com.gura.boot07.shop.dto.ShopDto;

public interface ShopDao {
   //상품 목록을 리턴해주는 메소드
   public List<ShopDto> getList();
   
   //상품 재고를 감소 시키는 메소드
   public void minusCount(int num);
   
   //잔고 감소 시키는 메소드 
   public void minusMoney(ShopDto dto);
   
   //포인트를 증가 시키는 메소드
   public void plusPoint(ShopDto dto);
   
   //상품의 가격을 리턴해주는 메소드
   public int getPrice(int num);
}