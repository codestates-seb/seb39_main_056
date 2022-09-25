package com.noterror.app.api.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPatchDto {
    private Long productId;
    private String productName;
    private int price;
    private int quantity;
    private String thumbnailImage;
    private String detailImage;
}
