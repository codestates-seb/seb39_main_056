package com.noterror.app.api.cart.repository;


import com.noterror.app.api.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
