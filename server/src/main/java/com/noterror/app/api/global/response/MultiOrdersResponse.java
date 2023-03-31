package com.noterror.app.api.global.response;

import com.noterror.app.api.orders.dto.OrderResponseDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiOrdersResponse<T> {
    private List<OrderResponseDto> orders;
    private PageInfo pageInfo;

    public MultiOrdersResponse(List<OrderResponseDto> orders, Page page) {
        this.orders = orders;
        this.pageInfo = new PageInfo(
                page.getNumber()+1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }
}

