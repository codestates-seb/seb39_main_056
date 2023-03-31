package com.noterror.app.api.cart.dto;

import com.noterror.app.api.entity.cart.Cart;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponseDto {

    private Long cartId;
    private Long memberId;
    private List<CartDetailResponseDto> cartDetails;

    public CartResponseDto(Cart cart) {
        this.cartId = cart.getCartId();
        this.memberId = cart.getMember().getMemberId();
        this.cartDetails = cart.getCartDetails().stream()
                .map(CartDetailResponseDto::new)
                .collect(Collectors.toList());
    }
}
