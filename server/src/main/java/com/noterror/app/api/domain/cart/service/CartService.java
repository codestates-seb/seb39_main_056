package com.noterror.app.api.domain.cart.service;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.cart.CartDetail;
import com.noterror.app.api.entity.member.Member;

public interface CartService {
    /**
     * 장바구니 제품 추가
     * @CASE1 : 동일한 제품이 이미 추가되어있다면 구매 개수만 추가
     * @CASE2 : 동일한 제품이 없다면, 새롭게 추가
     * @param cartDetail : 현재 회원의 장바구니에 추가되는 제품 정보(수량)
     * @return
     */
    Cart addProductInCart(CartDetail cartDetail) ;

    /**
     * 회원의 장바구니 제품 전체 조회
     * @param currentUser
     * @return
     */
    Cart getCartOfMember(Member currentUser);

    /**
     * 장바구니 수량 변경
     * @param updatePurchaseQuantityDto
     * @return
     */
    Cart updateCart();

    /**
     * 장바구니 제품 삭제
     * @param cartDetailId
     */
    void deleteCart(Long cartDetailId);


}
