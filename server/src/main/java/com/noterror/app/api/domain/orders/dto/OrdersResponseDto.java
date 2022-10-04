package com.noterror.app.api.domain.orders.dto;

import com.noterror.app.api.domain.entity.order.OrdersStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponseDto {
    private Long ordersId;
    private OrdersStatus ordersStatus;
    private LocalDateTime ordersDate;
    private List<OrdersProduct> ordersProducts = new ArrayList<>();
}


