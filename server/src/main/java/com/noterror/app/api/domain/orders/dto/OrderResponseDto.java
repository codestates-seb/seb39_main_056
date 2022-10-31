package com.noterror.app.api.domain.orders.dto;

import com.noterror.app.api.entity.order.Orders;
import com.noterror.app.api.entity.order.OrdersStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderResponseDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private OrdersStatus ordersStatus;
    private int totalPrice;
    private List<OrderDetailResponseDto> orderProductList;
    //private int productNumber = orderProductList.size();

    public OrderResponseDto(Orders order) {
        this.orderId = order.getOrdersId();
        this.orderDate = order.getCreateDate();
        this.ordersStatus = order.getOrderStatus();
        this.totalPrice = order.getOrderTotalPrice();
        this.orderProductList = order.getOrderDetails().stream()
                .map(OrderDetailResponseDto::new)
                .collect(Collectors.toList());
    }
}
