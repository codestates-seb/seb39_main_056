package com.noterror.app.api.domain.product.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
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
    private int quantity;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private LocalDateTime signDate = LocalDateTime.now();

    @Column(nullable = false)
    private String thumbnailImage;

    @Column(nullable = false)
    private  String detailImage;

    //TODO 식재료,카테고리 추가
}
