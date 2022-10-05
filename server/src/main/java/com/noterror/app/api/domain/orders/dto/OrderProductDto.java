package com.noterror.app.api.domain.orders.dto;

import com.noterror.app.api.domain.entity.order.OrderProduct;
import lombok.*;

/**
 * 주문 상품 정보를 담을 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class OrderProductDto {
    private Long productId;
    private String productName;
    private int quantity;

    private int productPrice;

    public OrderProductDto(OrderProduct orderProduct){
        this.productId = orderProduct.getProduct().getProductId();
        this.productName = orderProduct.getProduct().getProductName();
        this.quantity = orderProduct.getQuantity();
        this.productPrice = orderProduct.getProduct().getPrice();

    }
}
