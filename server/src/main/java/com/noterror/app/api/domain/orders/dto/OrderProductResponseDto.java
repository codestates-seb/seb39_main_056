package com.noterror.app.api.domain.orders.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class OrderProductResponseDto {
    private Long productId;
    private int quantityOfProduct;
    private int priceOfProduct; // Todo 수량대비 제품 가격 로직 작성
}
