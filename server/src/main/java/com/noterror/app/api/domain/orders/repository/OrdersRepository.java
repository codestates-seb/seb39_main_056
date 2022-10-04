package com.noterror.app.api.domain.orders.repository;

import com.noterror.app.api.domain.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
