package com.noterror.app.stubData;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public class ProductStubData {

    /**
     * @return ProductRequestDto
     */
    public static ProductRequestDto requestProductData() {
        return new ProductRequestDto(
                "카레라면",
                3, 10000,
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA",
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA");
    }

    /**
     * @return ProductResponseDto
     */
    public static ProductResponseDto responseProductData() {
        return new ProductResponseDto(
                1L,
                "카레라면",
                3, 10000,
                LocalDateTime.now(),
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA",
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA"
        );
    }

    /**
     * @Method 제품 데이터 1번
     * @return Product
     */
    public static Product getProduct_1() {
        return Product.builder()
                .productId(1L)
                .productName("카레라면")
                .price(10000)
                .quantity(3)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .signDate(LocalDateTime.now())
                .build();
    }

    /**
     * @Method 제품 데이터 2번
     * @return Product
     */
    public static Product getProduct_2() {
        return Product.builder()
                .productId(2L)
                .productName("옥수수식빵")
                .price(2000)
                .quantity(2)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .signDate(LocalDateTime.now().plusDays(1))
                .build();
    }

    /**
     * @Mehtod 신제품순으로 정렬된 제품 리스트
     * @return List<Product>
     */
    public static List<Product> getProductsSortSignDate() {
        return List.of(getProduct_2(), getProduct_1());
    }
}
