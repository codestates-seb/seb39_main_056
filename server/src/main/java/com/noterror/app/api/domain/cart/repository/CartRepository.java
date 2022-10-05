package com.noterror.app.api.domain.cart.repository;


import com.noterror.app.api.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
