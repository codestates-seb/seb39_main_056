package com.noterror.app.api.domain.cart.service;
import com.noterror.app.api.domain.cart.dto.CartProductDto;
import com.noterror.app.api.domain.entity.Cart;
import com.noterror.app.api.domain.entity.CartDetail;

import java.util.List;


public interface CartService {

    /**
     * 장바구니 추가
     * @param
     * @param
     * @param
     * @return
     */
    Long addCart(CartProductDto cartProductDto, Long memberId) ;

    /**
     * 장바구니 수량 변경
     * @param cartDetailId
     * @param count
     */
    void updateCart(Long cartDetailId, int count);

    /**
     * 장바구니 전체 조회
     *
     * @param cart
     * @return
     */
    List<CartDetail> listCart(Cart cart);

    /**
     * 장바구니 제품 삭제
     * @param cartDetailId
     */
    void deleteCart(Long cartDetailId);

    /**
     * 장바구니 전체 삭제
     * @param memberId
     */
    void deleteCartAll(Long memberId);



}
