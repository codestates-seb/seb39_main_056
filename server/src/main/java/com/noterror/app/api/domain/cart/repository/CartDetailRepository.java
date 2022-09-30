package com.noterror.app.api.domain.cart.repository;

import com.noterror.app.api.domain.cart.dto.CartDetailDto;
import com.noterror.app.api.domain.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    @Override
    CartDetail getReferenceById(Long aLong);

    @Query(value = "select cart_id, product_id from cart_detail", nativeQuery = true)
    CartDetail findByCartIdAndProductId(Long cartId, Long productId);

    //@Query(value = "select * from cart_detail join ", nativeQuery = true )
    @Query("select new com.noterror.app.api.domain.cart.dto.CartDetailDto(ci.cartDetailId, ci.product.productName, ci.product.price, ci.count) " +
            "from CartDetail ci "+
            "where ci.cart.cartId = :cartId ")
    List<CartDetailDto> findCartDetailDtoList(@Param("cartId") Long cartId);

    //@Query("select new com.noterror.app.api.domain.cart.dto.CartDetailDto(cd.cartDetailId, cd.productName, cd.price, cd.count)" +
           // "from CartDetail cd" + "where cd.cart.id = : cartId")
    //List<CartDetailDto> findCartDetailDtoList(Long cartId);
}
