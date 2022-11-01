package com.noterror.app.api.domain.orders.service;

import com.noterror.app.api.domain.orders.repository.OrdersRepository;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.cart.CartDetail;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.entity.order.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    @Override
    public Page<Orders> getOrderList(Member member, int page, int size) {
        PageRequest pageRequestInfo = getPageInfo(page-1, size);
        return ordersRepository.findAllByMember(member, pageRequestInfo);
    }

    @Override
    @Transactional
    public Orders orderProduct(Orders orderInfo) {
        orderInfo.applyQuantityDecrease();
        return ordersRepository.save(orderInfo);
    }

    @Override
    @Transactional
    public Orders orderProductsInCart(Cart cart) {
        Orders newOrder = orderFromCart(cart);
        newOrder.addMember(cart.getMember());
        newOrder.applyQuantityDecrease();
        return ordersRepository.save(newOrder);
    }

    private Orders orderFromCart(Cart cart) {
        Orders order = new Orders();
        List<CartDetail> cartDetailList = cart.getCartDetails();
        setNewOrderByCartDetailList(order, cartDetailList);
        return order;
    }

    private void setNewOrderByCartDetailList(Orders newOrder, List<CartDetail> cartDetailList) {
        cartDetailList.stream()
                .map(CartDetail::toOrderDetailByCartDetail)
                .forEach(newOrder::addOrderDetail);
    }

    private PageRequest getPageInfo(int page, int size) {
        return PageRequest.of(page, size, Sort.by("ordersId").descending());
    }
}
