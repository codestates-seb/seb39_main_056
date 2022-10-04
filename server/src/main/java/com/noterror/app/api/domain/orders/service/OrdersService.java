package com.noterror.app.api.domain.orders.service;

import com.noterror.app.api.domain.entity.order.Orders;
import com.noterror.app.api.domain.orders.dto.OrderDto;
import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersService {

    Page<OrderInfoDto> getOrderList(Long memberId, Pageable pageable);
    OrderResponseDto orderProduct(OrderDto orderDto, Long memberId);
}
