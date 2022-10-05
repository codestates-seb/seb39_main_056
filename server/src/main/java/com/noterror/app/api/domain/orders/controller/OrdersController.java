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

    @GetMapping
    public ResponseEntity getOrders(){
        List<OrderInfoDto> pageRequest = ordersService.getOrderList(currentUserEmail());
        return new ResponseEntity(new MultiOrdersResponse(pageRequest), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addOrder(@RequestBody OrderDto orderDto) {

        OrderResponseDto orderResponseDto = ordersService.orderProduct(orderDto, currentUserEmail());
        return new ResponseEntity(new SingleOrderResponse<>(orderResponseDto),HttpStatus.OK);
    }

    private String currentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
