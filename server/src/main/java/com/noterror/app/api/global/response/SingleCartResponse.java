package com.noterror.app.api.global.response;

import lombok.Getter;

@Getter
public class SingleCartResponse<T> {

    private T cart;

    public SingleCartResponse(T cartDetail) {
        this.cart = cartDetail;
    }

}
