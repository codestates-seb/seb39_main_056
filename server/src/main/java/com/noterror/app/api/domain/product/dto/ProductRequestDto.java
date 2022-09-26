package com.noterror.app.api.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String productName;
    private int quantity;
    private int price;
    private String thumbnailImage;
    private  String detailImage;

}
