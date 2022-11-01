package com.noterror.app.api.domain.orders.service;

import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.entity.order.Orders;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrdersService {

    /**
     * 주문 전체 내역 조회
     * @param member
     * @return
     */
    Page<Orders> getOrderList(Member member, int page, int size);

    /**
     * 단일 제품 주문
     * @param order
     * @return
     */
    Orders orderProduct(Orders order);

    /**
     * 장바구니 내의 제품 전체 주문
     * @param cart
     * @return
     */
    Orders orderProductsInCart(Cart cart);
}
