package com.noterror.app.api.cart.service;

import com.noterror.app.api.cart.dto.CartDetailUpdateInfoDto;
import com.noterror.app.api.cart.repository.CartDetailRepository;
import com.noterror.app.api.cart.repository.CartRepository;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.cart.CartDetail;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;

    @Override
    @Transactional
    public Cart addProductInCart(CartDetail cartDetailInfo) {
        cartDetailInfo = checkAlreadyExistProduct(cartDetailInfo);
        Cart currentCart = cartDetailInfo.getCart();
        currentCart.addCartDetail(cartDetailInfo);
        return cartRepository.save(currentCart);
    }

    @Override
    public Cart getCartOfMember(Member currentUser) {
        Long cartId = getCartIdOfMember(currentUser);
        return getCart(cartId);
    }

    @Override
    @Transactional
    public Cart updateCartDetail(CartDetailUpdateInfoDto updateInfo) {
        CartDetail currentCartDetail = getCartDetail(updateInfo.getCartDetailId());
        currentCartDetail.updatePurchaseQuantity(updateInfo.getPurchaseQuantity());
        Cart currentCart = currentCartDetail.getCart();
        return cartRepository.save(currentCart);
    }

    @Override
    @Transactional
    public void deleteCart(Long cartDetailId) {
        cartDetailRepository.delete(getCartDetail(cartDetailId));
    }

    private CartDetail checkAlreadyExistProduct(CartDetail cartDetailInfo) {
        Product wishProduct = cartDetailInfo.getProduct();

        if (isExistInCart(wishProduct)) {
            cartDetailInfo = getCartDetailByProduct(wishProduct);
            cartDetailInfo.plusPurchaseQuantity(cartDetailInfo.getPurchaseQuantity());
        }
        return cartDetailInfo;
    }

    private CartDetail getCartDetailByProduct(Product product) {
        return cartDetailRepository.findByProductId(product.getProductId()).get();
    }

    private boolean isExistInCart(Product product) {
        return cartDetailRepository
                .findByProductId(product.getProductId())
                .isPresent();
    }

    private CartDetail getCartDetail(Long cartDetailId) {
        return cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CART_DETAIL_NOT_FOUND));
    }

    private Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    private Long getCartIdOfMember(Member currentUser) {
        return currentUser.getCart().getCartId();
    }
}