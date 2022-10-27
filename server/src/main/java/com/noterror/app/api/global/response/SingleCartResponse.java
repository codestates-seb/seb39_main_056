package com.noterror.app.api.global.response;

public class SingleCartResponse<T> {
    private T cart;
    public SingleCartResponse(T cart) {
        this.cart = cart;
    }
}
