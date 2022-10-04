package com.noterror.app.api.domain.orders.service;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.entity.order.Orders;
import com.noterror.app.api.domain.entity.order.OrderProduct;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.orders.dto.OrderDto;
import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderProductDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import com.noterror.app.api.domain.orders.repository.OrdersRepository;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OrderInfoDto> getOrderList(Long memberId){
        List<Orders> orders = ordersRepository.findAll();

        List<Orders> memberOrder = new ArrayList<>();

        for (Orders data : orders) {
            if (data.getMember().getMemberId() == memberId) {
                memberOrder.add(data);
            }
        }
        List<OrderInfoDto> result = new ArrayList<>();
        List<OrderProductDto> orderDtoList = new ArrayList<>();

        for (Orders order : memberOrder) {
            List<OrderProduct> orderProducts = order.getOrderProducts();
            for(OrderProduct orderProduct : orderProducts) {
                OrderProductDto orderProductDto = new OrderProductDto(orderProduct.getProduct().getProductId(),orderProduct.getProduct().getProductName(), orderProduct.getQuantity(), orderProduct.getProduct().getPrice());
                orderDtoList.add(orderProductDto);
            }
            OrderInfoDto infoDto = new OrderInfoDto(order.getOrdersId(), order.getOrdersDate(), order.getOrdersStatus(), order.getTotalPrice(), orderDtoList);
            result.add(infoDto);
        }

        return result;

    }
     //제품 상세페이지에서 주문
    public OrderResponseDto orderProduct(OrderDto orderDto, Long memberId) {
        Product product = productRepository.findById(orderDto.getProductId()).get();
        Member member = memberRepository.findById(memberId).get();

        List<OrderProduct> orderProductList = new ArrayList<>();        //주문할 상품을 담을 리스트
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getQuantity());   //주문상품 엔티티 생성
        orderProductList.add(orderProduct);

        Orders order = Orders.createOrder(member, orderProductList);
        ordersRepository.save(order);
        OrderProductDto productDto = new OrderProductDto(orderProduct.getProduct().getProductId(), orderProduct.getProduct().getProductName(),orderProduct.getQuantity(), orderProduct.getProduct().getPrice());
        List<OrderProductDto> dtoList = new ArrayList<>();
        dtoList.add(productDto);
        OrderResponseDto responseDto = new OrderResponseDto(order.getOrdersId(), order.getOrdersStatus(), order.getOrdersDate(), order.getTotalPrice(), dtoList);

        return responseDto;
    }

}

