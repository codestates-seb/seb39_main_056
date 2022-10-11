package com.noterror.app.api.domain.orders.dto;

import com.noterror.app.api.entity.cart.CartDetail;
import lombok.*;

/**
 * 제품 상세 페이지에서 주문 하기 위한 dto
 */
@NoArgsConstructor
@Getter
public class OrderDto {
    private Long productId;
    private int ordersQuantity;

    public void of(CartDetail cartDetail) {
        this.productId = cartDetail.getProduct().getProductId();
        this.ordersQuantity = cartDetail.getPurchaseQuantity();
    }
}