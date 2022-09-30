package com.noterror.app.api.global.response;

import java.util.List;

public class MultiCartResponse<T> {
    private List<T> cartProducts;

    public MultiCartResponse(List<T> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
