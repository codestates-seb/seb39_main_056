package com.noterror.app.api.domain.product.controller;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class GetProductsOfProductControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ProductRepository productRepository;

    static Product dataInDB_1;
    static Product dataInDB_2;
    static Product dataInDB_3;

    @BeforeEach
    void beforeEach() throws InterruptedException {
        Product data_1
                = Product.builder()
                .productName("카레라면")
                .price(10000)
                .quantity(3)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .build();

        // 제일 최신 등록
        Product data_2
                = Product.builder()
                .productName("옥수수식빵")
                .price(2000)
                .quantity(2)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .build();

        // 제일 낮은 가격
        Product data_3
                = Product.builder()
                .productName("가지돈까스")
                .price(500)
                .quantity(7)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .build();

        dataInDB_1 = productRepository.save(data_1);
        dataInDB_1.setSignDate(LocalDateTime.now());
        dataInDB_2 = productRepository.save(data_2);
        dataInDB_2.setSignDate(LocalDateTime.now().plusDays(3));
        dataInDB_3 = productRepository.save(data_3);
        dataInDB_3.setSignDate(LocalDateTime.now().plusDays(1));
    }

    @AfterEach()
    void afterEach() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("제품 전체 조회 성공 테스트 - 한 페이지당 40개 데이터 조회")
    void getProducts_size_40() throws Exception {

        mockMvc.perform(
                        get("/products/list")
                                .param("size","40"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageInfo.size").value(40));
    }

    // 출력 순서 : 2 -> 3 -> 1
    @Test
    @DisplayName("제품 전체 조회 성공 테스트 - param 에 아무 것도 없을 때(default)")
    void getProducts_sort_newProduct() throws Exception {

        mockMvc.perform(
                        get("/products/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[0].productId").value(dataInDB_2.getProductId()))
                .andExpect(jsonPath("$.products[1].productId").value(dataInDB_3.getProductId()))
                .andExpect(jsonPath("$.products[2].productId").value(dataInDB_1.getProductId()))
                .andExpect(jsonPath("$.pageInfo.totalElements").value(3))
                .andExpect(jsonPath("$.sortInfo.sort").value("signDate"))
                .andExpect(jsonPath("$.sortInfo.orderBy").value("desc"));
    }

    // 출력 순서 : 3 -> 2 -> 1
    @Test
    @DisplayName("제품 전체 조회 성공 테스트 - 낮은 가격순 정렬")
    void getProducts_sort_price_orderBy_asc() throws Exception {

        mockMvc.perform(
                        get("/products/list")
                                .param("sort","price")
                                .param("orderBy","asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[0].productId").value(dataInDB_3.getProductId()))
                .andExpect(jsonPath("$.products[1].productId").value(dataInDB_2.getProductId()))
                .andExpect(jsonPath("$.products[2].productId").value(dataInDB_1.getProductId()))
                .andExpect(jsonPath("$.pageInfo.totalElements").value(3))
                .andExpect(jsonPath("$.sortInfo.sort").value("price"))
                .andExpect(jsonPath("$.sortInfo.orderBy").value("asc"));
    }

    // 출력 순서 : 1 -> 2 -> 3
    @Test
    @DisplayName("제품 전체 조회 성공 테스트 - 높은 가격순 정렬")
    void getProducts_sort_price_orderBy_desc() throws Exception {

        mockMvc.perform(
                        get("/products/list")
                                .param("sort","price")
                                .param("orderBy","desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[0].productId").value(dataInDB_1.getProductId()))
                .andExpect(jsonPath("$.products[1].productId").value(dataInDB_2.getProductId()))
                .andExpect(jsonPath("$.products[2].productId").value(dataInDB_3.getProductId()))
                .andExpect(jsonPath("$.pageInfo.totalElements").value(3))
                .andExpect(jsonPath("$.sortInfo.sort").value("price"))
                .andExpect(jsonPath("$.sortInfo.orderBy").value("desc"));
    }






}
