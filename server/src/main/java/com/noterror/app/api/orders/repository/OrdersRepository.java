package com.noterror.app.api.orders.repository;

import com.noterror.app.api.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends JpaRepository<Orders, Long>, OrdersRepositoryExtension {

    @Query(value = "select count(order_id) from orders " +
            "where orders.member.member_id = :memberId ", nativeQuery = true)
    int countOrder(@Param("memberId") Long memberId);

}
