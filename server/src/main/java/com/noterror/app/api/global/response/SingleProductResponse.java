package com.noterror.app.api.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SingleProductResponse<T> {
 
    private T product;
    
    public SingleProductResponse(T product){
        this.product = product;
    }

}
