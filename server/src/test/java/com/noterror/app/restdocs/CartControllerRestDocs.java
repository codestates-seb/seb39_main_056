package com.noterror.app.restdocs;

import com.google.gson.Gson;
import com.noterror.app.api.domain.cart.controller.CartController;
import com.noterror.app.api.domain.cart.dto.CartDetailDto;
import com.noterror.app.api.domain.cart.dto.CartProductDto;
import com.noterror.app.api.domain.cart.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class CartControllerRestDocs {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    Gson gson;
    @MockBean
    private CartService cartService;

    @Test
    @DisplayName("장바구니 제품 추가 API 문서화")
    void addCart() throws Exception {
        CartProductDto request = new CartProductDto(1L, 3);
        String jsonRequest = gson.toJson(request);

        CartDetailDto response = new CartDetailDto(1L, "카레라면", 20000, 3);
        Long memberId = 1L;
        given(cartService.addCart(any(), anyLong()))
                .willReturn(response);

        ResultActions action = mockMvc.perform(
                post("/{member-id}/cart", memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest));

        action.andExpect(status().isOk())
                .andDo(document("post-cart",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("productId").type(JsonFieldType.NUMBER).description("제품 식별자"),
                                        fieldWithPath("count").type(JsonFieldType.NUMBER).description("제품 수량")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("cartDetail").type(JsonFieldType.OBJECT).description("장바구니에 담긴 제품 데이터"),
                                        fieldWithPath("cartDetail.cartDetailId").type(JsonFieldType.NUMBER).description("장바구니에 담긴 제품 식별자"),
                                        fieldWithPath("cartDetail.productName").type(JsonFieldType.STRING).description("장바구니에 담긴 제품명"),
                                        fieldWithPath("cartDetail.price").type(JsonFieldType.NUMBER).description("장바구니에 담긴 제품의 가격"),
                                        fieldWithPath("cartDetail.count").type(JsonFieldType.NUMBER).description("장바구니에 담긴 제품의 수량")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("장바구니 제품 수량 변경 API 문서화")
    void updateCartProduct() throws Exception {
        Long memberId = 1L;
        Long cartDetailId = 2L;

        CartProductDto request = new CartProductDto(1L, 3);
        String jsonRequest = gson.toJson(request);

        CartProductDto response = new CartProductDto(1L, 5);
        given(cartService.updateCart(anyLong(), Mockito.any(request.getClass()))).willReturn(response);

        ResultActions actions = mockMvc.perform(
                patch("/{member-id}/cart/{cartDetail-id}", memberId, cartDetailId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest));

        actions.andExpect(status().isOk())
                .andDo(document("update-cart-product-count",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자"),
                                parameterWithName("cartDetail-id").description("장바구니 제품 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("productId").type(JsonFieldType.NUMBER).description("제품 식별자"),
                                        fieldWithPath("count").type(JsonFieldType.NUMBER).description("제품 수량")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("cartDetail").type(JsonFieldType.OBJECT).description("장바구니에 담긴 제품 데이터"),
                                        fieldWithPath("cartDetail.productId").type(JsonFieldType.NUMBER).description("제품 식별자"),
                                        fieldWithPath("cartDetail.count").type(JsonFieldType.NUMBER).description("제품 변경 수량")
                                )
                        )));
    }

    @Test
    @DisplayName("장바구니 제품 조회 API 문서화")
    void viewCartProduct() throws Exception {

}
