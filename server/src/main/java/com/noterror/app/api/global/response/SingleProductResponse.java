package com.noterror.app.api.global.response;

import com.noterror.app.api.domain.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class SingleProductResponse<T> {

    T product;

    public SingleProductResponse(T product) {
        this.product = product;
    }
}
