package com.noterror.app.api.domain.cart.service;

import com.noterror.app.api.domain.cart.dto.CartDetailDto;
import com.noterror.app.api.domain.cart.dto.CartPatchDto;
import com.noterror.app.api.domain.cart.dto.CartProductDto;
import com.noterror.app.api.domain.cart.repository.CartDetailRepository;
import com.noterror.app.api.domain.cart.repository.CartRepository;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.cart.CartDetail;
import com.noterror.app.api.entity.member.Member;
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

    @Override
    @Transactional
    public CartDetailDto addCart(CartProductDto cartProductDto, String email) {

        Member member = memberRepository.findByEmail(email).get();
        Product product = productRepository.findById(cartProductDto.getProductId()).get();
        Cart cart = member.getCart();

        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        //상품이 장바구니에 있는지 없는지 조회
        //CartDetail existProduct = cartDetailRepository.findByCartIdAndProductId(cart.getCartId(), cartProductDto.getProductId());
        CartDetail existProduct = cartDetailRepository.findByProductId(product.getProductId());
        // 만약 상품이 이미 있으면은 개수를 +
        if (existProduct != null) {
            existProduct.addPurchaseQuantity(cartProductDto.getPurchaseQuantity());
            //cartDetailRepository.save(existProduct);

            CartDetailDto cartDetailDto = new CartDetailDto();
            cartDetailDto.setCartDetailId(existProduct.getCartDetailId());
            cartDetailDto.setProductName(existProduct.getProduct().getProductName());
            cartDetailDto.setPrice(existProduct.getProduct().getPrice());
            cartDetailDto.setPurchaseQuantity(existProduct.getPurchaseQuantity());
            cartDetailDto.setThumbnailImage(existProduct.getProduct().getThumbnailImage());
            return cartDetailDto;
        } else { // 아니면은 CartItem 에 상품 저장
            CartDetail cartItem = CartDetail.createCartDetail(cart, product, cartProductDto.getPurchaseQuantity());
            cartDetailRepository.save(cartItem);

            CartDetailDto cartDetailDto = new CartDetailDto();
            cartDetailDto.setCartDetailId(cartItem.getCartDetailId());
            cartDetailDto.setProductName(cartItem.getProduct().getProductName());
            cartDetailDto.setPrice(cartItem.getProduct().getPrice());
            cartDetailDto.setPurchaseQuantity(cartItem.getPurchaseQuantity());
            cartDetailDto.setThumbnailImage(cartItem.getProduct().getThumbnailImage());
            return cartDetailDto;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartDetailDto> listCart(String email) {
        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email).get();
        Cart cart = member.getCart();

        if (cart == null) {
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartDetailRepository.findCartDetailDtoList(cart.getCartId());

        return cartDetailDtoList;
    }


    //장바구니 상품수량 up
    @Override
    public CartPatchDto updateCart(CartPatchDto cartPatchDto) {
        CartDetail cartDetail = cartDetailRepository.findById(cartPatchDto.getCartDetailId()).get();
        cartDetail.updatePurchaseQuantity(cartPatchDto.getPurchaseQuantity());

        //cartDetailRepository.save(cartDetail);
        CartPatchDto cartUpdateDto = new CartPatchDto();
        cartUpdateDto.setCartDetailId(cartPatchDto.getCartDetailId());
        cartUpdateDto.setPurchaseQuantity(cartPatchDto.getPurchaseQuantity());
        return cartUpdateDto;
    }

    @Override
    public void deleteCart(Long cartDetailId) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId).get();
        cartDetailRepository.delete(cartDetail);
    }


}