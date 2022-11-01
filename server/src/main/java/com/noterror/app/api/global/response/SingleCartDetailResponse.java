package com.noterror.app.api.global.response;

import lombok.Getter;

@Getter
public class SingleCartDetailResponse<T> {

    private T cartDetail;

    public SingleCartDetailResponse(T cartDetail) {
        this.cartDetail = cartDetail;
    }

}
