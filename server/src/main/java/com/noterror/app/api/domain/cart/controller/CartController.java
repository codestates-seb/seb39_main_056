package com.noterror.app.api.domain.cart.controller;

import com.noterror.app.api.domain.cart.service.CartService;
import com.noterror.app.api.domain.entity.Cart;
import com.noterror.app.api.domain.entity.CartDetail;
import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.member.memberService.MemberService;
import com.noterror.app.api.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/user")
@RestController
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class CartController {
    private final MemberService memberService;
    private final ProductService productService;
    private final CartService cartService;

    /**
     * 장바구니 추가
     *
     * @param memberId
     * @param productId
     * @param count
     * @return
     */
    @PostMapping("cart/list/{product-id}")
    public String addCartProduct(Long memberId,
                                 @PathVariable("product-id") Long productId,
                                 int count) {
        Member member = memberService.findExistsMember(memberId);
        Product product = productService.findExistProduct(productId);

        cartService.addCart(member, product, count);

        return "redirect:/product/detail/{product-id}"; //메인페이지로? 해당제품페이지?
    }

    /**
     * 장바구니 조회
     * @param memberId
     * @return
     */
    @GetMapping("cart/list")
    public ResponseEntity viewCartProduct(Long memberId, Model model){
        Member memberExist = memberService.findExistsMember(memberId);
        Cart memberCart = memberExist.getCart(); //회원의 장바구니를 가져오기

        //장바구니에 든 제품
        List<CartDetail> cartDetailList = cartService.listCart(memberCart);

        //장바구니 총가격
        int totalPrice = 0;

        for (CartDetail cartDetail : cartDetailList) {
            totalPrice += cartDetail.getCount() * cartDetail.getProduct().getPrice();
        }
        model.addAttribute("cartDetail", cartDetailList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("member", memberService.findExistsMember(memberId));

        return null; //장바구니로 이동
    }

    /**
     * 장바구니 삭제
     * @param memberId
     * @param cartDetailId
     * @return
     */
    @DeleteMapping("cart/list/{cartDetail-id}/delete")
    public ResponseEntity deleteCartProduct(Long memberId,
                                    @PathVariable("cartDetail-id") Long cartDetailId){
        //장바구니 내역에서 상품 아이디로 상품 찾기
        CartDetail deleteProduct = cartService.findExistCartDetail(cartDetailId, memberId);

        cartService.deleteCart(cartDetailId);

        Cart memberCart = cartService.findExistCart(memberId);

        List<CartDetail> cartDetailList = cartService.listCart(memberCart);

        int totalPrice = 0;
        for (CartDetail cartDetail : cartDetailList) {
            totalPrice += cartDetail.getCount() * cartDetail.getProduct().getPrice();
        }

        memberCart.setCount(memberCart.getCount()-deleteProduct.getCount());

        return null; //회원장바구니
    }

    /**
     * 장바구니 수량 변경
     * @param cartDetail
     * @return
     */
    @PostMapping("{member-id}/cart/{cartDetail-id}/update")
    public ResponseEntity updateCartProduct(CartDetail cartDetail) {
        cartService.updateCart(cartDetail);

        return null; //회원장바구니
    }
}
