package com.noterror.app.api.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
}
