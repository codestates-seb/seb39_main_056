package com.noterror.app.api.domain.cart.controller;

import com.noterror.app.api.domain.cart.dto.CartDetailDto;
import com.noterror.app.api.domain.cart.dto.CartPatchDto;
import com.noterror.app.api.domain.cart.dto.CartProductDto;
import com.noterror.app.api.domain.cart.service.CartService;
import com.noterror.app.api.global.response.MultiCartsResponse;
import com.noterror.app.api.global.response.SingleCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * 장바구니 제품 추가
     */
    @PostMapping
    public @ResponseBody ResponseEntity addCartProduct(@RequestBody @Valid CartProductDto cartProductDto) {
        CartDetailDto cartDetail = cartService.addCart(cartProductDto, getCurrentUserEmail());
        return new ResponseEntity(new SingleCartResponse(cartDetail), HttpStatus.OK);
    }

    /**
     * 장바구니 조회
     */
    @GetMapping
    public @ResponseBody ResponseEntity viewCartProduct() {

        List<CartDetailDto> cartDetailList = cartService.listCart(getCurrentUserEmail());

        return new ResponseEntity(new MultiCartsResponse(cartDetailList), HttpStatus.OK);
    }

    /**
     * 장바구니 제품 수량 변경
     */
    @PutMapping
    public @ResponseBody ResponseEntity updateCartProduct(@RequestBody @Valid CartPatchDto cartPatchDto) {
        if(cartPatchDto.getPurchaseQuantity() < 0) {
            return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        }
        CartPatchDto cartDetail = cartService.updateCart(cartPatchDto);
        return new ResponseEntity(new SingleCartResponse(cartDetail), HttpStatus.OK);
    }

    /**
     * 장바구니 제품 삭제
     */
    @DeleteMapping("/{cart-detail-id}")
    public @ResponseBody ResponseEntity deleteCartProduct(@PathVariable("cart-detail-id") Long cartDetailId) {
        cartService.deleteCart(cartDetailId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private String getCurrentUserEmail() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return currentUserEmail;
    }
}