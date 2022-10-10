package com.noterror.app.api.domain.orders.dto;

import com.noterror.app.api.entity.order.OrdersStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto {
    private Long ordersId;
    private OrdersStatus ordersStatus;
    private int totalPrice;
    private LocalDateTime orderDate;
    private List<OrderProductDto> orderProductList = new ArrayList<>();
}


