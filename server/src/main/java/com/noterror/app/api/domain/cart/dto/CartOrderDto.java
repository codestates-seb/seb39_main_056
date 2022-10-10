package com.noterror.app.api.domain.cart.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 주문 상품을 전달할 DTO
 * 여러 개의 상품을 주문하기때문에 자기 자신을 List로 가짐
 */
@Getter
@Setter
public class CartOrderDto {
    //private Long cartDetailId;

    private List<CartOrderDto> cartOrderDtoList;
}
