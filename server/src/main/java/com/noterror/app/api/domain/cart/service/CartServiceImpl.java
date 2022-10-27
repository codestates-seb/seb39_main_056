package com.noterror.app.api.domain.cart.service;

import com.noterror.app.api.domain.cart.repository.CartDetailRepository;
import com.noterror.app.api.domain.cart.repository.CartRepository;
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
        Product wishProduct = cartDetailInfo.getProduct();
        getCartDetail(cartDetailInfo, wishProduct);
        Cart currentCart = cartDetailInfo.getCart();
        currentCart.addCartDetail(cartDetailInfo);
        return cartRepository.save(currentCart);
    }

    @Override
    public Cart getCartOfMember(Member currentUser) {
        Long cartId = getCartId(currentUser);
        return getCart(cartId);
    }

    private Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    private Long getCartId(Member currentUser) {
        return currentUser.getCart().getCartId();
    }


    //장바구니 상품수량 up
    @Override
    @Transactional
    public UpdatePurchaseQuantityDto updateCart(UpdatePurchaseQuantityDto updatePurchaseQuantityDto) {
        CartDetail cartDetail = cartDetailRepository.findById(updatePurchaseQuantityDto.getCartDetailId()).get();
        cartDetail.updatePurchaseQuantity(updatePurchaseQuantityDto.getPurchaseQuantity());

        //cartDetailRepository.save(cartDetail);
        UpdatePurchaseQuantityDto cartUpdateDto = new UpdatePurchaseQuantityDto();
        cartUpdateDto.setCartDetailId(updatePurchaseQuantityDto.getCartDetailId());
        cartUpdateDto.setPurchaseQuantity(updatePurchaseQuantityDto.getPurchaseQuantity());
        return cartUpdateDto;
    }

    @Override
    @Transactional
    public void deleteCart(Long cartDetailId) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId).get();
        cartDetailRepository.delete(cartDetail);
    }

    private void getCartDetail(CartDetail cartDetailInfo, Product wishProduct) {
        if (isExistProductInCart(wishProduct)) {
            addPurchaseQuantityOfExistsProduct(
                    cartDetailInfo.getPurchaseQuantity(),
                    wishProduct);
        }
    }

    private void addPurchaseQuantityOfExistsProduct(int purchaseQuantity, Product product) {
        CartDetail existsCartDetail = getCartDetailByProduct(product);
        existsCartDetail.plusPurchaseQuantity(purchaseQuantity);
    }

    private CartDetail getCartDetailByProduct(Product product) {
        return cartDetailRepository
                .findByProductId(product.getProductId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.PRODUCT_NOT_FOUND));
    }

    private boolean isExistProductInCart(Product product) {
        return cartDetailRepository
                .findByProductId(product.getProductId())
                .isPresent();
    }
}