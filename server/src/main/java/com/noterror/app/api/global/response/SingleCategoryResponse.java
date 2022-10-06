package com.noterror.app.api.global.response;

import lombok.Getter;

@Getter
public class SingleCategoryResponse<T> {

    T category;

    public SingleCategoryResponse(T category) {
        this.category = category;
    }
}
