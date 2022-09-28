package com.noterror.app.api.domain.cart.service;
import com.noterror.app.api.domain.entity.Cart;
import com.noterror.app.api.domain.entity.CartDetail;
import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.entity.Product;

import java.util.List;

public interface CartService {
    /**
     * 장바구니 생성
     * @param member
     */
    void createCart(Member member);

    /**
     * 장바구니 제품 추가
     * @param member
     * @param product
     * @param count
     */
    void addCart(Member member, Product product, int count);

    /**
     * 장바구니 제품 수량 수정
     * @param cartDetail
     */
    void updateCart(CartDetail cartDetail);

    /**
     * 장바구니 한 개 제품 삭제
     * @param cartDetailId
     */
    void deleteCart(Long cartDetailId);

    /**
     * 장바구니 여러 제품 삭제
     */
    void deleteCarts(List<CartDetail> cartDetails);

    /**
     * 장바구니 모두 비우기
     * @param cartId
     */
    void deleteCartAll(Long cartId);

    /**
     * 장바구니 목록 조회
     * @param cart
     * @return
     */
    List<CartDetail> listCart(Cart cart);

    /**
     * 장바구니 금액 총 합
     * @return
     */
    int totalMoney();
}
