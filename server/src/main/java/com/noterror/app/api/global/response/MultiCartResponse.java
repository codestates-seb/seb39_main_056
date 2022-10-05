package com.noterror.app.api.global.response;

import lombok.Getter;

import java.util.List;

@Getter
public class MultiCartResponse<T> {
    private List<T> cartProducts;

    public MultiCartResponse(List<T> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
