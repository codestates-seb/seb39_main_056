package com.noterror.app.api.domain.orders.dto;

import com.noterror.app.api.entity.order.Orders;
import com.noterror.app.api.entity.order.OrdersStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 정보를 담을 dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class OrderInfoDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private OrdersStatus ordersStatus;

    private int totalPrice;

    public OrderInfoDto(Orders order) {
        this.orderId = order.getOrdersId();
        this.orderDate = order.getCreateDate();
        this.ordersStatus = order.getOrdersStatus();
        this.totalPrice = order.getTotalPrice();
    }

    //주문한 상품들을 담을 리스트
    public List<OrderProductDto> orderProductList = new ArrayList<>();

    public void addOrderProductDto(OrderProductDto orderProductDto) {
        orderProductList.add(orderProductDto);
    }
}
