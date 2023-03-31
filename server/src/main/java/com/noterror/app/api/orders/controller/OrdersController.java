package com.noterror.app.api.orders.controller;

import com.noterror.app.api.cart.service.CartService;
import com.noterror.app.api.member.service.MemberService;
import com.noterror.app.api.orders.dto.OrderDetailDto;
import com.noterror.app.api.orders.dto.OrderResponseDto;
import com.noterror.app.api.orders.service.OrdersService;
import com.noterror.app.api.product.service.ProductService;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.entity.order.OrderDetail;
import com.noterror.app.api.entity.order.Orders;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import com.noterror.app.api.global.response.MultiOrdersResponse;
import com.noterror.app.api.global.response.SingleOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrdersController {

    private final OrdersService ordersService;
    private final MemberService memberService;
    private final ProductService productService;

    /**
     * 주문 내역 조회
     * 페이지네이션 기능 추가
     */
    @GetMapping("/list")
    public ResponseEntity getOrderList(@RequestParam(required = false, defaultValue = "1") int page,
                                       @RequestParam(required = false, defaultValue = "10") int size) {

        Page<Orders> ordersInPage = ordersService.getOrderList(getMemberByEmail(), page, size);
        List<OrderResponseDto> response = toListOfOrderResponses(ordersInPage);
        return new ResponseEntity(
                new MultiOrdersResponse(response, ordersInPage), HttpStatus.OK);
    }

    /**
     * 단일 주문
     */
    @PostMapping("/product/{product-id}")
    public ResponseEntity OrderSingleProduct(@PathVariable("product-id") Long productId,
                                             @RequestBody @Valid OrderDetailDto orderDetailDto) {
        OrderDetail currentOrderProductInfo = getOrderDetail(productId, orderDetailDto);
        Orders singleOrder = setOrderInfo(currentOrderProductInfo);
        Orders newOrder = ordersService.orderProduct(singleOrder);
        OrderResponseDto response = new OrderResponseDto(newOrder);
        return new ResponseEntity(
                new SingleOrderResponse<>(response), HttpStatus.OK);
    }

    /**
     * 장바구니 내역 전체 주문
     */
    @PostMapping("/cart")
    public ResponseEntity orderProductsInCart() {
        Cart cartOfMember = getCartByMember();
        Orders newOrder = ordersService.orderProductsInCart(cartOfMember);
        OrderResponseDto response = new OrderResponseDto(newOrder);
        return new ResponseEntity(
                new SingleOrderResponse(response), HttpStatus.OK);
    }

    /**
     * TODO 주문 취소
     */
    @DeleteMapping("/{orders-id}")
    public ResponseEntity deleteOrder(@PathVariable("orders-id") Long ordersId) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private Member getMemberByEmail() {
        return memberService.findMemberByEmail(getCurrentUserEmail());
    }

    private Product getProduct(Long productId) {
        return productService.findProduct(productId);
    }

    private OrderDetail getOrderDetail(Long productId, OrderDetailDto orderDetailDto) {
        return orderDetailDto.toOrderDetailWithProduct(
                getProduct(productId)
        );
    }

    private Orders setOrderInfo(OrderDetail currentOrderProductInfo) {
        return currentOrderProductInfo.toOrdersWithMember(getMemberByEmail());
    }

    private Cart getCartByMember() {
        return getMemberByEmail().getCart();
    }

    private List<OrderResponseDto> toListOfOrderResponses(Page<Orders> ordersInPage) {
        return ordersInPage.stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

}
