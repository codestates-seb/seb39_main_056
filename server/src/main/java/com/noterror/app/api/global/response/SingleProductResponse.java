package com.noterror.app.api.global.response;

import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.vegeType.dto.VegeTypeResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class SingleProductResponse<T> {
 
    private T product;
    
    public SingleProductResponse(T product){
        this.product = product;
    }

}
