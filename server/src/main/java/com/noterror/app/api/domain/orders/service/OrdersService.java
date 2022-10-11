package com.noterror.app.api.domain.orders.service;

import com.noterror.app.api.domain.orders.dto.OrderDto;
import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;

import java.util.List;

public interface OrdersService {

    /**
     * 주문 전체 내역 조회
     * @param email
     * @return
     */
    List<OrderInfoDto> getOrderList(String email);

    /**
     * 단일 제품 주문
     * @param orderDto
     * @param email
     * @return
     */
    OrderResponseDto orderProduct(OrderDto orderDto, String email);

    /**
     * 장바구니 내의 제품 전체 주문
     * @param email
     * @return
     */
    OrderInfoDto orderCartProducts(String email);
}
