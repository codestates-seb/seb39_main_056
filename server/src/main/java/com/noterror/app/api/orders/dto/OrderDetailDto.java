package com.noterror.app.api.orders.dto;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.cart.CartDetail;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.entity.order.OrderDetail;
import com.noterror.app.api.entity.order.Orders;
import lombok.*;

import javax.validation.constraints.Min;

/**
 * 제품 주문을 위한 DTO
 */
@Getter
public class OrderDetailDto {

    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int orderQuantity;

    public OrderDetail toOrderDetailWithProduct(Product product) {
        return OrderDetail.builder()
                .orderQuantity(this.orderQuantity)
                .orders(new Orders())
                .product(product)
                .build();
    }
}