package com.noterror.app.api.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private int price;
    private int quantity;
    private LocalDateTime signDate;
    private String thumbnailImage;
    private String detailImage;

    // TODO : 식재료
    private List<String> ingredients;
    // TODO : 카테고리
    private List<String> categories;


}
