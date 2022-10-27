package com.noterror.app.api.domain.cart.dto;

import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.cart.CartDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 장바구니 Response dto
 */
@Setter
@Getter
@NoArgsConstructor
public class CartDetailResponseDto {
    private Long cartDetailId;
    private Long productId;
    private String productName;
    private int price;
    private int purchaseQuantity;
    private String thumbnailImage;

    public CartDetailResponseDto(Cart cart) {
        CartDetail cartDetail = getCartDetail(cart);
        Product product = cartDetail.getProduct();
        this.cartDetailId = cartDetail.getCartDetailId();
        this.productId = product.getProductId();
        this.thumbnailImage = product.getThumbnailImage();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.purchaseQuantity = cartDetail.getPurchaseQuantity();
    }

    public CartDetailResponseDto(CartDetail cartDetail) {
        Product product = cartDetail.getProduct();
        this.cartDetailId = cartDetail.getCartDetailId();
        this.productId = product.getProductId();
        this.thumbnailImage = product.getThumbnailImage();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.purchaseQuantity = cartDetail.getPurchaseQuantity();
    }

    private CartDetail getCartDetail(Cart cart) {
        return cart.getCartDetails().get(cart.getCartDetails().size() - 1);
    }
}

