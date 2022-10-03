package com.noterror.app.api.domain.cart.service;
import com.noterror.app.api.domain.cart.dto.CartDetailDto;
import com.noterror.app.api.domain.cart.dto.CartPatchDto;
import com.noterror.app.api.domain.cart.dto.CartProductDto;

import java.util.List;


public interface CartService {
    /**
     * 장바구니 제품 추가
     * @param cartProductDto
     * @param memberId
     * @return
     */
    CartDetailDto addCart(CartProductDto cartProductDto, Long memberId) ;

    /**
     * 장바구니 수량 변경
     * @param cartPatchDto
     * @return
     */
    CartPatchDto updateCart(CartPatchDto cartPatchDto);

    /**
     * 장바구니 조회
     * @param memberId
     * @return
     */
    List<CartDetailDto> listCart(Long memberId);

    /**
     * 장바구니 제품 삭제
     * @param cartDetailId
     */
    void deleteCart(Long cartDetailId);
}
