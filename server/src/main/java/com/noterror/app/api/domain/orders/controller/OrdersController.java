package com.noterror.app.api.domain.orders.controller;

import com.noterror.app.api.domain.orders.dto.OrderDto;
import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import com.noterror.app.api.domain.orders.service.OrdersService;
import com.noterror.app.api.global.response.MultiOrdersResponse;
import com.noterror.app.api.global.response.SingleOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping("{member-id}/orders")
    public ResponseEntity getOrders(@PathVariable("member-id") Long memberId){
        List<OrderInfoDto> pageRequest = ordersService.getOrderList(memberId);
        return new ResponseEntity(new MultiOrdersResponse(pageRequest), HttpStatus.OK);
    }

    @PostMapping("{member-id}/orders")
    public ResponseEntity addOrder(@PathVariable("member-id") Long memberId, @RequestBody OrderDto orderDto) {
        OrderResponseDto orderResponseDto = ordersService.orderProduct(orderDto, memberId);
        return new ResponseEntity(new SingleOrderResponse<>(orderResponseDto),HttpStatus.OK);
    }

}
