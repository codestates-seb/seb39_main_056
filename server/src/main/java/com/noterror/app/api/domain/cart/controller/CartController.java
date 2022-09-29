package com.noterror.app.api.domain.cart.controller;

import com.noterror.app.api.domain.cart.dto.CartDetailDto;
import com.noterror.app.api.domain.cart.dto.CartProductDto;
import com.noterror.app.api.domain.cart.service.CartService;
import com.noterror.app.api.domain.entity.Cart;
import com.noterror.app.api.domain.entity.CartDetail;
import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.global.response.SingleCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    /**
     * 장바구니 제품 추가
     * @param cartProductDto
     * @param
     * @return
     */
    @PostMapping("cart")
    public ResponseEntity addCartProduct(@RequestBody CartProductDto cartProductDto, Long memberId) {

        Long cartDetailId = cartService.addCart(cartProductDto, memberId );

        return new ResponseEntity( new SingleCartResponse(cartDetailId), HttpStatus.OK);
    }

    /**
     * 장바구니 조회
     * @param
     * @return
     */
    @GetMapping("cart")
    public ResponseEntity viewCartProduct(Cart cart) {
        List<CartDetail> cartDetailList = cartService.listCart(cart);

        return new ResponseEntity( new SingleCartResponse(cartDetailList), HttpStatus.OK);
    }



    /**
     * 장바구니 수량 변경
     * @param cartDetailId
     * @param count
     * @return
     */
    @PatchMapping("cartDetail/{cartDetail-id}")
    public ResponseEntity updateCartProduct(@PathVariable("cartDetail-id") Long cartDetailId, int count) {
        if(count<0) {
            return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        }
        cartService.updateCart(cartDetailId, count);
        return new ResponseEntity(new SingleCartResponse(cartDetailId), HttpStatus.OK);
    }

    /**
     * 장바구니 제품 삭제
     * @param cartDetailId
     * @return
     */
    @DeleteMapping("cartDetail/{cartDetail-id}")
    public ResponseEntity deleteCartProduct(@PathVariable("cartDetail-id") Long cartDetailId) {
        cartService.deleteCart(cartDetailId);
        return new ResponseEntity(new SingleCartResponse(cartDetailId), HttpStatus.OK);
    }


    /**
     * 장바구니 전체 삭제
     * @param member
     */
   @DeleteMapping("cartDetail")

    public void deleteCartProductAll(Member member) {
        cartService.deleteCartAll(member.getMemberId());
    }
}