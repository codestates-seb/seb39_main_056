package com.noterror.app.api.domain.product.dto;

import com.noterror.app.api.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private int quantity;
    private int price;
    private LocalDateTime signDate;
    private String thumbnailImage;
    private  String detailImage;

    public ProductResponseDto(Product product){
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.signDate = product.getSignDate();
        this.thumbnailImage = product.getThumbnailImage();
        this.detailImage = product.getDetailImage();
    }

}
