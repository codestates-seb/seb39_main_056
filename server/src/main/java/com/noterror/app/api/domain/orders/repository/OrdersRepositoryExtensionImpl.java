package com.noterror.app.api.domain.orders.repository;

import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.entity.order.Orders;
import com.noterror.app.api.entity.order.QOrders;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class OrdersRepositoryExtensionImpl extends QuerydslRepositorySupport implements OrdersRepositoryExtension {

    public OrdersRepositoryExtensionImpl() {
        super(Orders.class);
    }

    @Override
    public Page<Orders> findAllByMember(Member member, Pageable pageable) {
        QOrders orders = QOrders.orders;
        JPQLQuery<Orders> query = from(orders).where(orders.member.memberId.eq(member.getMemberId()));
        JPQLQuery<Orders> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Orders> fetchResults = pageableQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
