package com.noterror.app.api.domain.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class OrdersProductResponseDto {
    private Long productId;
    private int quantityOfProduct;
    private int priceOfProduct; // Todo 수량대비 제품 가격 로직 작성
}
