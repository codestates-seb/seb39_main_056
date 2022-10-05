package com.noterror.app.api.domain.orders.repository;

import com.noterror.app.api.domain.entity.order.OrderProduct;
import com.noterror.app.api.domain.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
