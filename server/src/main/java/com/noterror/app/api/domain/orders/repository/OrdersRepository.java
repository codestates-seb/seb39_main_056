package com.noterror.app.api.domain.orders.repository;

import com.noterror.app.api.domain.entity.order.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query(value = "select o from orders o " +
            "where o.member.member_id = :memberId " +
            "order by o.orders_date desc", nativeQuery = true)
    List<Orders> findOrders(@Param("memberId") Long memberId, Pageable pageable);

    @Query(value = "select count(o) from orders o " +
            "where o.member.member_id = :memberId ",nativeQuery = true)
    Long countOrder(@Param("memberId") Long memberId);
}
