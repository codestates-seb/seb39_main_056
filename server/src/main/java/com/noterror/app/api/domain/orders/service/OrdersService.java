package com.noterror.app.api.domain.orders.service;

import com.noterror.app.api.domain.entity.order.Orders;
import com.noterror.app.api.domain.orders.dto.OrderDto;
import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrdersService {

    List<OrderInfoDto> getOrderList(Long memberId);
    OrderResponseDto orderProduct(OrderDto orderDto, Long memberId);
}
