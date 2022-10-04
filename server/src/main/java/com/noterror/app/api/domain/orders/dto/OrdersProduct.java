package com.noterror.app.api.domain.orders.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersProduct {
    private int quantity;
    private String productName;
    private int ordersPrice;

    public OrdersProduct(com.noterror.app.api.domain.entity.order.OrdersProduct ordersProduct){
        this.productName = ordersProduct.getProduct().getProductName();
        this.quantity = ordersProduct.getQuantity();
        this.ordersPrice = ordersProduct.getOrdersPrice();

    }

}
