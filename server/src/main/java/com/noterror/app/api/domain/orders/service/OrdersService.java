package com.noterror.app.api.domain.orders.service;

import com.noterror.app.api.domain.orders.dto.OrderDto;
import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;

import java.util.List;

public interface OrdersService {

    List<OrderInfoDto> getOrderList(String email);
    OrderResponseDto orderProduct(OrderDto orderDto, String email);
}
