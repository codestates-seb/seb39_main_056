package com.noterror.app.api.global.response;

import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
public class MultiOrdersResponse<T> {
    private List<OrderInfoDto> orders;


    public MultiOrdersResponse(List<OrderInfoDto> orders) {
        this.orders = orders;

    }
}

