package com.noterror.app.api.cart.repository;

import com.noterror.app.api.entity.cart.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query(value = "select " +
            "cart_detail_id, cart_id, product_id, purchase_quantity " +
            "from cart_detail " +
            "where product_id = :productId", nativeQuery = true)
    Optional<CartDetail> findByProductId(@Param("productId") Long productId);

}

