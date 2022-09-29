package com.noterror.app.api.domain.cart.dto;

import com.noterror.app.api.domain.entity.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 장바구니 담기 dto
 */
@Getter
@Setter
@Data
public class CartProductDto {
    @NotNull
    private Product product;

    @Min(1)
    private int count;

}
