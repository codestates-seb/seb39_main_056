package com.noterror.app.api.domain.orders.controller;

import com.noterror.app.api.domain.entity.order.Orders;
import com.noterror.app.api.domain.orders.dto.OrderDto;
import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import com.noterror.app.api.domain.orders.service.OrdersService;
import com.noterror.app.api.global.response.MultiOrdersResponse;
import com.noterror.app.api.global.response.SingleOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping({"{member-id}/orders", "{member-id}/orders/{page}"})
    public ResponseEntity getOrders(@PathVariable("member-id") Long memberId,
                                    @PathVariable("page")Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5); //한번에 가져올 주문 5개

        Page<OrderInfoDto> orderInfoDtoList = ordersService.getOrderList(memberId, pageable);
        return new ResponseEntity(new MultiOrdersResponse<>(orderInfoDtoList, pageable), HttpStatus.OK);
    }

    @PostMapping("{member-id}/orders")
    public ResponseEntity addOrder(@PathVariable("member-id") Long memberId, @RequestBody OrderDto orderDto) {
        OrderResponseDto orderResponseDto = ordersService.orderProduct(orderDto, memberId);
        return new ResponseEntity(new SingleOrderResponse<>(orderResponseDto),HttpStatus.OK);
    }
}
