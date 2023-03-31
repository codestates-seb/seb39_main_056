package com.noterror.app.api.orders.dto;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.order.OrderDetail;
import lombok.*;

@Getter
@Setter
public class OrderDetailResponseDto {
    private Long orderDetailId;
    private int ordersQuantity;
    private int productTotalPrice;
    private Long orderId;
    private Product product;

    public OrderDetailResponseDto(OrderDetail orderDetail) {
        this.orderDetailId = orderDetail.getOrderDetailId();
        this.ordersQuantity = orderDetail.getOrderQuantity();
        this.productTotalPrice = orderDetail.getProductTotalPrice();
        this.orderId = orderDetail.getOrders().getOrdersId();
        this.product = orderDetail.getProduct();
    }
}
