package com.noterror.app.api.domain.product.dto;

import com.noterror.app.api.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseDto {
        private Long productId;
        private String productName;
        private int price;
        private int quantity;
        private String thumbnailImage;
        private String detailImage;
        private LocalDateTime signDate;
    }