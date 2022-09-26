package com.noterror.app.api.global.response;

import lombok.Getter;

@Getter
public class SingleProductResponse<T> {

    T product;

    public SingleProductResponse(T product){
        this.product = product;
    }
}
