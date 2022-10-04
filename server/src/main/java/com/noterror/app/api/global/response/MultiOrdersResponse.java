package com.noterror.app.api.global.response;

import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
public class MultiOrdersResponse<T> {
    private Page<OrderInfoDto> orders;
    private Pageable pageInfo;

    public MultiOrdersResponse(Page<OrderInfoDto> orders, Pageable page) {
        this.orders = orders;
        this.pageInfo = page;

    }
}

