package com.noterror.app.api.domain.cart.controller;

import com.noterror.app.api.domain.cart.dto.CartDetailDto;
import com.noterror.app.api.domain.cart.dto.CartPatchDto;
import com.noterror.app.api.domain.cart.dto.CartProductDto;
import com.noterror.app.api.domain.cart.service.CartService;
import com.noterror.app.api.global.response.MultiCartResponse;
import com.noterror.app.api.global.response.SingleCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
     * @param memberId
     * @return
     */
    @PostMapping("cart")
    public @ResponseBody ResponseEntity addCartProduct(@RequestBody CartProductDto cartProductDto) {
        CartDetailDto cartDetail = cartService.addCart(cartProductDto, getCurrentUserEmail());
        return new ResponseEntity(new SingleCartResponse(cartDetail), HttpStatus.OK);
    }



    /**
     * 장바구니 조회
     * @param memberId
     * @return
     */
    @GetMapping("cart")
    public @ResponseBody ResponseEntity viewCartProduct() {

        List<CartDetailDto> cartDetailList = cartService.listCart(getCurrentUserEmail());

        return new ResponseEntity(new MultiCartResponse(cartDetailList), HttpStatus.OK);
    }

    /**
     * 장바구니 제품 수량 변경
     * @param memberId
     * @param cartPatchDto
     * @return
     */
    @PutMapping("cart")
    public @ResponseBody ResponseEntity updateCartProduct(@RequestBody CartPatchDto cartPatchDto) {
        if(cartPatchDto.getCount() < 0) {
            return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        }
        CartPatchDto cartDetail = cartService.updateCart(cartPatchDto);
        return new ResponseEntity(new SingleCartResponse(cartDetail), HttpStatus.OK);
    }

    /**
     * 장바구니 제품 삭제
     * @param cartDetailId
     * @return
     */
    @DeleteMapping("cart/{cartDetail-id}")
    public @ResponseBody ResponseEntity deleteCartProduct(@PathVariable("cartDetail-id") Long cartDetailId) {
        cartService.deleteCart(cartDetailId);
        return new ResponseEntity(new SingleCartResponse(cartDetailId), HttpStatus.OK);
    }

    private String getCurrentUserEmail() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return currentUserEmail;
    }
}