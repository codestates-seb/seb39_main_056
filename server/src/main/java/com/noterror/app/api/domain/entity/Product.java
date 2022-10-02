package com.noterror.app.api.domain.entity;

import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    private LocalDateTime signDate;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String thumbnailImage;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String detailImage;

    // TODO : 카테고리

    //== BUSINESS LOGIC ==//
    public void updateProductInfo(ProductRequestDto productPatchDto) {
        this.productName = productPatchDto.getProductName();
        this.quantity = productPatchDto.getQuantity();
        this.price = productPatchDto.getPrice();
        this.thumbnailImage = productPatchDto.getThumbnailImage();
        this.detailImage = productPatchDto.getDetailImage();
    }
}