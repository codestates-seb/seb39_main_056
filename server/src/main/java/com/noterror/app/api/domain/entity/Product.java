package com.noterror.app.api.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder
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

    @Column(nullable = false)
    private LocalDateTime signDate = LocalDateTime.now();

    @Column(nullable = false)
    private String thumbnailImage;

    @Column(nullable = false)
    private String detailImage;

    // TODO : 채식 유형
    // TODO : 식재료
    // TODO : 카테고리 연관매핑

}
