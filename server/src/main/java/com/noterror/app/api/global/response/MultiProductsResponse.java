package com.noterror.app.api.global.response;

import com.noterror.app.api.global.sort.Sort;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiProductsResponse<T> {

    private List<T> products;
    private PageInfo pageInfo;
    private SortInfo sortInfo;

    public MultiProductsResponse(List<T> products, Page page, Sort sort) {
        this.products = products;
        this.pageInfo = new PageInfo(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
        this.sortInfo = new SortInfo(
                sort.getSort(),
                sort.getOrderBy()
        );
    }
}
