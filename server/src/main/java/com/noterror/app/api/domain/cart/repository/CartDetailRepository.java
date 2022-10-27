package com.noterror.app.api.domain.cart.repository;

import com.noterror.app.api.domain.cart.dto.CartDetailResponseDto;
import com.noterror.app.api.entity.cart.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    @Override
    CartDetail getReferenceById(Long aLong);

    @Query(value = "select " +
            "cart_detail_id, cart_id, product_id, purchase_quantity " +
            "from cart_detail", nativeQuery = true)
    CartDetail findByCartIdAndProductId(Long cartId, Long productId);

    @Query(value = "select " +
            "cart_detail_id, cart_id, product_id, purchase_quantity " +
            "from cart_detail " +
            "where product_id = :productId", nativeQuery = true)
    Optional<CartDetail> findByProductId(@Param("productId") Long productId);

    @Query("select new com.noterror.app.api.domain.cart.dto.CartDetailDto" +
            "(ci.cartDetailId, ci.product.productName, ci.product.price, ci.purchaseQuantity, ci.product.thumbnailImage ) " +
            "from CartDetail ci " +
            "where ci.cart.cartId = :cartId ")
    List<CartDetailResponseDto> findCartDetailDtoList(@Param("cartId") Long cartId);

    @Query(value = "select " +
            "cart_detail_id, cart_id, product_id, purchase_quantity" +
            " from cart_detail" +
            " where cart_detail.cart.cart_id := cartId", nativeQuery = true)
    CartDetail findByCartId(@Param("cartId") Long cartId);
}

