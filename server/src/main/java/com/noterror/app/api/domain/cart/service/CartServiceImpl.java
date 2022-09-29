package com.noterror.app.api.domain.cart.service;

import com.noterror.app.api.domain.cart.dto.CartProductDto;
import com.noterror.app.api.domain.cart.repository.CartDetailRepository;
import com.noterror.app.api.domain.cart.repository.CartRepository;
import com.noterror.app.api.domain.entity.Cart;
import com.noterror.app.api.domain.entity.CartDetail;
import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    public void createCart(Member member) {
        Cart cart = Cart.createCart(member);
        cartRepository.save(cart);
    }

    @Transactional
    public Long addCart(CartProductDto cartProductDto, Long memberId) {
        Member member= memberRepository.findById(memberId).get();

        Product product = productRepository.findById(cartProductDto.getProduct().getProductId()).get();

        Cart cart = member.getCart();

        if( cart == null ){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        Long exId = cartProductDto.getProduct().getProductId();

        CartDetail savedCartItem = cartDetailRepository.getReferenceById(exId);

        // 만약 상품이 이미 있으면은 개수를 +
        if(savedCartItem != null){
            savedCartItem.addCount(cartProductDto.getCount());
            return savedCartItem.getCartDetailId();
        } else { // 아니면은 CartItem 에 상품 저장
            CartDetail cartItem = CartDetail.createCartDetail(cart, product, cartProductDto.getCount());
            cartDetailRepository.save(cartItem);
            return cartItem.getCartDetailId();
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<CartDetail> listCart(Cart cart) {
        List<CartDetail> cartList = cartDetailRepository.findAll();
        List<CartDetail> userCartList = new ArrayList<>();

        for(CartDetail cartDetail : cartList) {
            if(cartDetail.getCart().getCartId() == cart.getCartId()) {
                userCartList.add(cartDetail);
            }
        }
        return userCartList;
    }

    @Override
    public void updateCart(Long cartDetailId, int count){
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId).orElseThrow(NullPointerException::new);
        cartDetail.addCount(count);
    }

    @Override
    public void deleteCart(Long cartDetailId) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId).orElseThrow(NullPointerException::new);
        cartDetailRepository.delete(cartDetail);
    }

    @Override
    public void deleteCartAll(Long memberId) {
        List<CartDetail> products = cartDetailRepository.findAll();
        //해당하는 유저의 장바구니 내역 삭제
        for(CartDetail cartDetail : products){
            if(cartDetail.getCart().getMember().getMemberId() == memberId ){
                cartDetail.getCart().setCount(cartDetail.getCart().getCount() - 1);
                cartDetailRepository.deleteById(cartDetail.getCartDetailId());
            }
        }
    }
}