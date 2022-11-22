package com.noterror.app.api.domain.product.controller;

import com.google.gson.Gson;
import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import com.noterror.app.api.entity.Product;
import com.noterror.app.helper.WithAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Admin 의 제품 관리 기능 테스트
 */
@SpringBootTest
@AutoConfigureMockMvc
public class FeatureTestOfAdminProductController {

    @Autowired private MockMvc mockMvc;
    @Autowired private ProductRepository productRepository;
    @Autowired private Gson gson;

    static Product preProduct;
    static Product dataInDb;

    @BeforeEach
    void beforeEach() {
        preProduct = Product.builder()
                .productName("카레라면")
                .price(10000)
                .stockQuantity(3)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .build();

        dataInDb = productRepository.save(preProduct);
    }

    @AfterEach()
    void afterEach() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("제품 수정 성공 - 정상 입력, 제품명만 변경")
    @WithAccount("admin")
    void 제품_수정_테스트() throws Exception {

        // 카레라면 -> 사과하세우
        ProductRequestDto request = new ProductRequestDto(
                "사과하세우",
                3, 10000,
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA",
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA",
                "비건"
        );

        String jsonOfRequest = gson.toJson(request);

        mockMvc.perform(
                put("/admin/products/edit/{product-id}", dataInDb.getProductId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonOfRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.productName").value(request.getProductName()))
                .andExpect(jsonPath("$.product.price").value(preProduct.getPrice()));
    }

    @Test
    @DisplayName("제품 삭제 성공")
    @WithAccount("admin")
    void 제품_삭제_테스트() throws Exception {

        ResultActions actions = mockMvc.perform(
                        delete("/admin/products/edit/{product-id}", dataInDb.getProductId()))
                .andExpect(status().isNoContent());

        assertTrue(productRepository.findAll().size()==0);
    }
}
