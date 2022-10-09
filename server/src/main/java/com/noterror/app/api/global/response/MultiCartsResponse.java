package com.noterror.app.api.global.response;

import lombok.Getter;

import java.util.List;

@Getter
public class MultiCartsResponse<T> {
    private List<T> carts;

    public MultiCartsResponse(List<T> cartProducts) {
        this.carts = cartProducts;
    }
}
