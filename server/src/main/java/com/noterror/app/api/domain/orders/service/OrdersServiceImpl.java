package com.noterror.app.api.domain.orders.service;

import com.noterror.app.api.domain.cart.repository.CartDetailRepository;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.cart.CartDetail;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.entity.order.Orders;
import com.noterror.app.api.entity.order.OrderProduct;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.orders.dto.OrderDto;
import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderProductDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import com.noterror.app.api.domain.orders.repository.OrderProductRepository;
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
    private final OrderProductRepository orderProductRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CartDetailRepository cartDetailRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OrderInfoDto> getOrderList(String email) {
        List<Orders> orders = ordersRepository.findAll();

        List<Orders> memberOrder = new ArrayList<>();

        Member member = memberRepository.findByEmail(email).get();

        for (Orders data : orders) {
            if (data.getMember().getMemberId() == member.getMemberId()) {
                memberOrder.add(data);
            }
        }
        List<OrderInfoDto> result = new ArrayList<>();
        //List<OrderProductDto> orderDtoList = new ArrayList<>();

        for (Orders order : memberOrder) {
            //List<OrderProduct> orderProducts = order.getOrderProducts();

            OrderInfoDto infoDto = new OrderInfoDto(order);

            for (OrderProduct orderProduct : order.getOrderProducts()) {
                OrderProductDto orderProductDto = new OrderProductDto(orderProduct);
                infoDto.addOrderProductDto(orderProductDto);
            }
            result.add(infoDto);
        }

        return result;
    }

    //제품 상세페이지에서 주문
    @Override
    public OrderResponseDto orderProduct(OrderDto orderDto, String email) {
        Product product = productRepository.findById(orderDto.getProductId()).get();
        Member member = memberRepository.findByEmail(email).get();

        List<OrderProduct> orderProductList = new ArrayList<>();        //주문할 상품을 담을 리스트
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getOrdersQuantity());   //주문상품 엔티티 생성
        orderProductRepository.save(orderProduct);
        orderProductList.add(orderProduct);

        Orders order = Orders.createOrder(member, orderProductList);
        ordersRepository.save(order);
        OrderProductDto productDto = new OrderProductDto(orderProduct.getProduct().getProductId(), orderProduct.getProduct().getProductName(), orderProduct.getOrdersQuantity(), orderProduct.getProduct().getPrice());
        List<OrderProductDto> dtoList = new ArrayList<>();
        dtoList.add(productDto);
        OrderResponseDto responseDto = new OrderResponseDto(order.getOrdersId(), order.getOrdersStatus(), order.getTotalPrice(), order.getCreateDate(), dtoList);

        return responseDto;
    }

    @Override
    public OrderInfoDto orderCartProducts(String email) {
        Member findMember = memberRepository.findByEmail(email).get();
        List<CartDetail> cartDetailList = findMember.getCart().getCartDetail();

        List<OrderDto> orderDtoList = new ArrayList<>();
        for (CartDetail cartDetail : cartDetailList) {
            OrderDto orderWishDto = new OrderDto();
            orderWishDto.of(cartDetail);
            orderDtoList.add(orderWishDto);
        }
        OrderInfoDto orderProductId = orderCartList(orderDtoList, findMember);

        for (CartDetail cartDetail : cartDetailList) {
            cartDetailRepository.deleteById(cartDetail.getCartDetailId());
        }

        return orderProductId;
    }

    //장바구니에서 주문할 상품 데이터를 전달받아 주문 생성
    public OrderInfoDto orderCartList(List<OrderDto> orderDtoList, Member member) {
        List<OrderProduct> orderProductList = new ArrayList<>();

        for (OrderDto orderDto : orderDtoList) {
            Product product = productRepository.findById(orderDto.getProductId()).get();
            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getOrdersQuantity());
            orderProductRepository.save(orderProduct);
            orderProductList.add(orderProduct);

        }

        Orders order = Orders.createOrder(member, orderProductList);
        ordersRepository.save(order);

        List<OrderProductDto> listOrderProduct = new ArrayList<>();
        for (OrderProduct orderProducts : orderProductList) {
            OrderProductDto orderProductDtos = new OrderProductDto(orderProducts.getProduct().getProductId(),
                    orderProducts.getProduct().getProductName(),
                    orderProducts.getOrdersQuantity(),
                    orderProducts.getProduct().getPrice());
            listOrderProduct.add(orderProductDtos);
        }

        OrderInfoDto result = new OrderInfoDto(order.getOrdersId(), order.getCreateDate(), order.getOrdersStatus(), order.getTotalPrice(), listOrderProduct);

        return result;
    }
}
