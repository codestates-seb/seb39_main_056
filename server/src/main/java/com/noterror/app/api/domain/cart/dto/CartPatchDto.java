package com.noterror.app.api.domain.cart.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 장바구니 수량 변경
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartPatchDto {
    @NotNull
    private Long cartDetailId;
    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int purchaseQuantity;

}