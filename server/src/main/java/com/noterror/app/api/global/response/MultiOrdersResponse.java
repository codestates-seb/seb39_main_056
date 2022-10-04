package com.noterror.app.api.global.response;

import com.noterror.app.api.global.sort.Sort;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
@Getter
public class MultiOrdersResponse<T> {
    private List<T> orders;
    private PageInfo pageInfo;

    public MultiOrdersResponse(List<T> orders, Page page) {
        this.orders = orders;
        this.pageInfo = new PageInfo(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());

    }
}

