package com.noterror.app.api.domain.orders.service;

import com.noterror.app.api.domain.entity.order.Orders;
import org.springframework.data.domain.Page;

public interface OrdersService {

    /**
     * 페이지네이션
      * @param page
     * @param size
     * @return
     */
    Page<Orders> findOrdersWithPage(int page, int size, Long memberId);

}
