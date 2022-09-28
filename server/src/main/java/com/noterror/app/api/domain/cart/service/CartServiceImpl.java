package com.noterror.app.api.domain.cart.service;

import com.noterror.app.api.domain.cart.repository.CartDetailRepository;
import com.noterror.app.api.domain.cart.repository.CartRepository;
import com.noterror.app.api.domain.entity.Cart;
import com.noterror.app.api.domain.entity.CartDetail;
import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDetailRepository cartDetailRepository;

    private final CartRepository cartRepository;

    @Override
    public void createCart(Member member) {
        Cart cart = Cart.CreateCart(member);
        cartRepository.save(cart);
    }

    @Override
    public void addCart(Member member, Product product, int count) {
        Cart cart = cartRepository.findByMemberId(member.getMemberId());

        CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(cart.getCartId(), product.getProductId());

        if (cartDetail == null) {
            cartDetail = cartDetail.createCartDetail(cart, product, count);
            cartDetailRepository.save(cartDetail);
            cart.setCount(cart.getCount() + 1);
        } else {
            cartDetail.addCount(count);
        }


    }

    @Override
    public void updateCart(CartDetail cartDetail) {

    }

    @Override
    public void deleteCart(Long productId) {

    }

    @Override
    public void deleteCarts(List<CartDetail> cartDetails) {

    }

    @Override
    public void deleteCartAll(Long memberId) {

    }

    @Override
    public List<CartDetail> listCart(String memberId) {
        return null;
    }

    @Override
    public int totalMoney() {
        return 0;
    }


}
