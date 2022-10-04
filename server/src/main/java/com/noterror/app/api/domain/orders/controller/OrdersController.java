package com.noterror.app.api.domain.orders.controller;

import com.noterror.app.api.domain.entity.order.Orders;
import com.noterror.app.api.domain.orders.dto.OrdersResponseDto;
import com.noterror.app.api.domain.orders.service.OrdersService;
import com.noterror.app.api.global.response.MultiOrdersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin
@Slf4j
@Validated
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;
    /**
     * 주문 전체 조회
     * @param page
     * @param size
     * @return
     */
    @GetMapping("{member-id}/list")
    public ResponseEntity getOrders(@PathVariable("member-id") Long memberId,
                                    @RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "5") int size) {

        Page<Orders> pageOrders =
                ordersService.findOrdersWithPage(page-1,size,memberId);
        List<Orders> orders = pageOrders.getContent();

        List<OrdersResponseDto> responses;


        return new ResponseEntity(
                new MultiOrdersResponse(responses,pageOrders
                ), HttpStatus.OK
        );
    }
}
