package com.noterror.app.api.domain.cart.dto;

import com.noterror.app.api.entity.cart.Cart;

import java.util.List;
import java.util.stream.Collectors;

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
