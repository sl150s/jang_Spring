package com.gura.spring04.shop.dao;

import com.gura.spring04.shop.dto.OrderDto;

public interface OrderDao {
	//배송 전보를 추가해주는 메소드
	public void addOrder(OrderDto dto);
}
