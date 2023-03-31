package com.noterror.app.api.cart.dto;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.cart.CartDetail;
import com.noterror.app.api.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * 장바구니 내의 제품 수량을 다루는 Dto
 * 1) 장바구니에 제품 추가 시
 * 2) 장바구니 안의 제품 수량 변경 시
 */
@Getter
@Setter
public class AddProductInCartDto {

    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int purchaseQuantity;

    public CartDetail toCartDetailWithMemberAndProduct(Member member, Product product) {
        return CartDetail.builder()
                .purchaseQuantity(this.purchaseQuantity)
                .cart(member.getCart())
                .product(product)
                .build();
    }
}
