package com.noterror.app.api.domain.cart.dto;

import com.noterror.app.api.domain.entity.Product;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 장바구니 담기 dto
 * 장바구니에 담을 제품의 id와 수량을 입력받음
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDto {
    private Long productId;

    @Min(1)
    private int count;

}
