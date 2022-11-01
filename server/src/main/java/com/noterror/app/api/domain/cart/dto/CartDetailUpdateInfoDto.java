package com.noterror.app.api.domain.cart.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Setter
@Getter
public class CartDetailUpdateInfoDto {

    private Long cartDetailId;

    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int purchaseQuantity;
}
