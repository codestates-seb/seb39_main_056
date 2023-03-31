package com.noterror.app.api.cart.dto;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.cart.CartDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 장바구니 Response dto
 */
@Setter
@Getter
@NoArgsConstructor
public class CartDetailResponseDto {
    private Long cartDetailId;
    private int purchaseQuantity;
    private Product product;

    public CartDetailResponseDto(Cart cart) {
        CartDetail cartDetail = getCartDetail(cart);
        this.cartDetailId = cartDetail.getCartDetailId();
        this.purchaseQuantity = cartDetail.getPurchaseQuantity();
        this.product = cartDetail.getProduct();
    }

    public CartDetailResponseDto(CartDetail cartDetail) {
        this.cartDetailId = cartDetail.getCartDetailId();
        this.purchaseQuantity = cartDetail.getPurchaseQuantity();
        this.product = cartDetail.getProduct();
    }

    private CartDetail getCartDetail(Cart cart) {
        return cart.getCartDetails().get(cart.getCartDetails().size() - 1);
    }
}

