package com.noterror.app.api.domain.orders.service;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.entity.order.Orders;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Orders> findOrdersWithPage(int page, int size, Long memberId) {

        return ordersRepository.findAll(
                PageRequest.of(page, size, Sort.by("ordersDate").descending()));
    }

}

