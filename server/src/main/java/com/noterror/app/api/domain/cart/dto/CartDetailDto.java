package com.noterror.app.api.domain.cart.dto;

import lombok.*;

/**
 * 장바구니 조회 Dto
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {
    private Long cartDetailId;

    private String productName;

    private int price;

    private int count;

/*
    public CartDetailDto(Long cartDetailId, String productName,
                         int price, int count){
        this.cartDetailId = cartDetailId;
        this.productName = productName;
        this.price = price;
        this.count = count;
    }

 */
}