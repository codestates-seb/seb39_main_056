package com.noterror.app.api.domain.cart.service;

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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    

    @Override
    @Transactional
    public void addCart(Member member, Product product, int count) {
        //장바구니 존재유무 확인
        Cart cart = cartRepository.findByMemberId(member.getMemberId());

        if (cart == null) {
            cart = Cart.CreateCart(member);
            cartRepository.save(cart);
        }
        //제품 존재 유무 확인
        Optional<Product> productCheck = productRepository.findById(product.getProductId());

        //상품이 장바구니에 존재하는지 확인
        CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(cart.getCartId(), product.getProductId());

        //상품이 장바구니에 없다면, 장바구니에 상품을 생성하고 추가
        if (cartDetail == null){
            cartDetail = CartDetail.createCartDetail(cart, product, count);
            cartDetailRepository.save(cartDetail);
            cart.setCount(cart.getCount() + 1);
        } else {
            cartDetail.addCount(count);
        }

    }

    @Override
    public void updateCart(CartDetail cartDetail){

    }

    @Override
    public void deleteCart(Long cartDetailId) {
        cartDetailRepository.deleteById(cartDetailId);

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

    @Override
    public List<CartDetail> listCart(Cart cart) {
        List<CartDetail> cartDetails = cartDetailRepository.findAll();
        List<CartDetail> products = new ArrayList<>();

        for(CartDetail cartDetail : cartDetails) {
            if(cartDetail.getCart().getCartId() == cart.getCartId()){
                products.add(cartDetail);
            }
        }
        return products;
    }

    public Cart findExistCart(Long memberId) {
        Cart cart = cartRepository.findByMemberId(memberId);
        return cart;
    }

    public CartDetail findExistCartDetail(Long cartId, Long productId) {
        CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(cartId, productId);
        return cartDetail;
    }
}
