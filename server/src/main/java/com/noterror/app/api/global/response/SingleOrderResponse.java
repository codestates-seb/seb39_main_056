package com.noterror.app.api.global.response;

import lombok.Getter;

@Getter
public class SingleOrderResponse<T> {

    private T orders;

    public SingleOrderResponse(T orders) {
        this.orders = orders;
    }
}
