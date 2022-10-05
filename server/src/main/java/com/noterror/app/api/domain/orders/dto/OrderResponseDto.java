package com.noterror.app.api.domain.orders.dto;

import com.noterror.app.api.entity.order.OrdersStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class OrderResponseDto {
    private Long ordersId;
    private OrdersStatus ordersStatus;
    private LocalDateTime ordersDate;
    private int totalPrice;
    private List<OrderProductDto> orderProductList = new ArrayList<>();
}


