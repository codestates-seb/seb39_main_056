package com.noterror.app.api.domain.orders.repository;

import com.noterror.app.api.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    /*@Query(value = "select orders_id, orders_date, orders_status" +
            "from orders " +
            "where orders.member.member_id = :memberId ", nativeQuery = true)
    List<Orders> findOrders(@Param("memberId") Long memberId);
     */
    @Query(value = "select count(orders_id) from orders " +
            "where orders.member.member_id = :memberId ",nativeQuery = true)
    int countOrder(@Param("memberId") Long memberId);

}
