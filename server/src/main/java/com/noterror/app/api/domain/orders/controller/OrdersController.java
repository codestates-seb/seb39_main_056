package com.noterror.app.api.domain.orders.controller;

import com.noterror.app.api.domain.orders.dto.OrderDto;
import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import com.noterror.app.api.domain.orders.service.OrdersService;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import com.noterror.app.api.global.response.MultiOrdersResponse;
import com.noterror.app.api.global.response.SingleOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    /**
     * 주문 내역 조회
     */
    @GetMapping
    public ResponseEntity getOrders() {
        List<OrderInfoDto> pageRequest = ordersService.getOrderList(getCurrentUserEmail());
        return new ResponseEntity(new MultiOrdersResponse(pageRequest), HttpStatus.OK);
    }

    /**
     * 단일 주문
     */
    @PostMapping
    public ResponseEntity addOrder(@RequestBody OrderDto orderDto) {

        OrderResponseDto orderResponseDto = ordersService.orderProduct(orderDto, getCurrentUserEmail());
        return new ResponseEntity(new SingleOrderResponse<>(orderResponseDto), HttpStatus.OK);
    }

    /**
     * 장바구니 내역 전체 주문
     * 패키지 이동(cart -> order)
     * 멤버가 갖고있는 cart 를 활용
     */
    @PostMapping("/cart")
    public @ResponseBody ResponseEntity orderCartProduct() {
        OrderInfoDto orderId = ordersService.orderCartProducts(getCurrentUserEmail());
        return new ResponseEntity(new SingleOrderResponse(orderId), HttpStatus.OK);
    }

    /**
     * TODO 주문 취소
     */
    @DeleteMapping("/{orders-id}")
    public @ResponseBody ResponseEntity deleteOrder(@PathVariable("orders-id") Long ordersId) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
