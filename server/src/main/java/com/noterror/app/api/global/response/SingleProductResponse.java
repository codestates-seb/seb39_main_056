package com.noterror.app.api.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SingleProductResponse<T> {
    private T data;

    public SingleProductResponse(T data){
        this.data = data;
    }
}
