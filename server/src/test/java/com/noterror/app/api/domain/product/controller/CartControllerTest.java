/*package com.noterror.app.api.domain.product.controller;

import com.google.gson.Gson;
import com.noterror.app.api.domain.cart.dto.CartProductDto;
import com.noterror.app.api.domain.cart.repository.CartDetailRepository;
import com.noterror.app.api.domain.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired private Gson gson;

    static CartDetail dbData;
    static CartDetail cartDetail;

    @BeforeEach
    void beforeEach() {
        Member member = new Member(1L, "홍", "hong@email.com", "01011111111", "12345", "제주시", "제주아파트","비건", LocalDateTime.now(), new Cart(), Role.USER);
        Product product = new Product(1L, "카레라면", 10000, 5, LocalDateTime.now(), "asdf", "asdfasdf", null);
        Cart cart = new Cart(1L, 10, member, null);
        cartDetail = new CartDetail(1L, 3, cart, product);

        dbData = cartDetailRepository.save(cartDetail);

    }

    @AfterEach
    void afterEach() {
        cartDetailRepository.deleteAll();
    }

    @Test
    @DisplayName("제품 수량 변경")
    void 제품_수량_변경_테스트() throws Exception {
        CartProductDto request = new CartProductDto(
                1L, 6);
        String jsonRequest = gson.toJson(request);

        mockMvc.perform(
                patch("/{member-id}/cart/{cartDetail-id}", dbData.getCart().getMember().getMemberId(),dbData.getCartDetailId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartDetail.productId").value(request.getProductId()))
                .andExpect(jsonPath("$.cartDetail.count").value(request.getCount()));



    }
}

 */
