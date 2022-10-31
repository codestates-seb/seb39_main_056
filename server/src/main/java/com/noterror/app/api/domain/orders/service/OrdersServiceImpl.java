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

        Orders orders = new Orders();
        List<CartDetail> cartDetails = cart.getCartDetails();
        cartDetails.stream()
                .map(CartDetail::toOrderDetailByCartDetail)
                .forEach(orderDetail -> orders.addOrderDetail(orderDetail));
        orders.addMember(cart.getMember());
        orders.applyQuantityDecrease();

        return ordersRepository.save(orders);
    }

    private PageRequest getPageInfo(int page, int size) {
        return PageRequest.of(page, size, Sort.by("ordersId").descending());
    }
}
