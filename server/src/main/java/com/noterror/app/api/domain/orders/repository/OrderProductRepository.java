package com.noterror.app.api.domain.orders.repository;

import com.noterror.app.api.entity.order.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
