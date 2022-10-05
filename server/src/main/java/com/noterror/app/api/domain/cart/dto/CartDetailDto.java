package com.noterror.app.api.domain.cart.dto;

import lombok.*;

/**
 * 장바구니 조회 Dto
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CartDetailDto {
    private Long cartDetailId; //장바구니에 담긴 상품 id
    private String productName; //장바구니에 담긴 상품 이름
    private int price;
    private int count;
    private String thumbnailImage;
}
