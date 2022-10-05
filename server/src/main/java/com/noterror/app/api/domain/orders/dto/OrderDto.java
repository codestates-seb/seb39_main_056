package com.noterror.app.api.domain.orders.dto;

import lombok.*;

/**
 * 제품 상세 페이지에서 주문 하기 위한 dto
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class OrderDto {
    private Long productId;

    private int quantity;
}
