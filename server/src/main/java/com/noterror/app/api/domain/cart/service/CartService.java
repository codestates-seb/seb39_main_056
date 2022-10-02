package com.noterror.app.api.domain.cart.service;
import com.noterror.app.api.domain.cart.dto.CartDetailDto;
import com.noterror.app.api.domain.cart.dto.CartProductDto;

import java.util.List;


public interface CartService {
    /**
     * 장바구니 제품 추가
     *
     * @param cartProductDto
     * @param memberId
     * @return
     */
    CartDetailDto addCart(CartProductDto cartProductDto, Long memberId) ;

    /**
     * 장바구니 수량 변경
     *
     * @param cartDetailId
     * @param count
     * @return
     */
    CartProductDto updateCart(Long cartDetailId, int count);

    /**
     * 장바구니 전체 조회
     *
     * @param memberId
     * @return
     */
    List<CartDetailDto> listCart(Long memberId);

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
