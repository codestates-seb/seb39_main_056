package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplSliceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("제품 조회 성공- 데이터 존재")
    void findProduct_exist_id() throws Exception {
        //given
        Product dataInDB = Product.builder()
                .productId(1L)
                .productName("카레라면")
                .price(10000)
                .quantity(3)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .signDate(LocalDateTime.now())
                .build();

        //when
        given(productRepository.findById(anyLong()))
                .willReturn(Optional.of(dataInDB));
        ProductResponseDto response = productService.findProduct(1L);

        //then
        assertEquals(response.getProductId(),dataInDB.getProductId());
        assertEquals(response.getProductName(), dataInDB.getProductName());
        assertEquals(response.getSignDate(), dataInDB.getSignDate());
    }

    @Test
    @DisplayName("제품 조회 실패 - NullPointException")
    void findProduct_fail() throws Exception {
        //given
        Product dataInDB = null;

        //when
        given(productRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(dataInDB));

        String result = "";
        try {
            productService.findProduct(1L);
        } catch (NullPointerException e) {
            result = e.getMessage();
        }

        //then
        assertTrue(result.equals("조회된 제품이 없습니다."));
    }
}