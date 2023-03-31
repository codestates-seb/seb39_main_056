package com.noterror.app.api.orders.repository;

import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.entity.order.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OrdersRepositoryExtension {

    Page<Orders> findAllByMember(Member member, Pageable pageable);
}
