package com.noterror.app.api.domain.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductPatchDto {
    private Long productId;
    private String productName;
    private int price;
    private int quantity;
    private String thumbnailImage;
    private String detailImage;

    public void setProductId(Long productId){
        this.productId = productId;
    }
}
