package com.noterror.app.restdocs;

import com.google.gson.Gson;
import com.noterror.app.api.domain.cart.controller.CartController;
import com.noterror.app.api.domain.cart.dto.CartDetailDto;
import com.noterror.app.api.domain.cart.dto.CartPatchDto;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
        given(cartService.addCart(any(), anyString()))
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

        CartPatchDto request = new CartPatchDto(1L, 3);
        String jsonRequest = gson.toJson(request);

        CartPatchDto response = new CartPatchDto(1L, 5);
        given(cartService.updateCart(Mockito.any(request.getClass()))).willReturn(response);

        ResultActions actions = mockMvc.perform(
                patch("/{member-id}/cart", memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest));

        actions.andExpect(status().isOk())
                .andDo(document("update-cart-product-count",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("cartDetailId").type(JsonFieldType.NUMBER).description("장바구니 제품 식별자"),
                                        fieldWithPath("count").type(JsonFieldType.NUMBER).description("제품 수량")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("cartDetail").type(JsonFieldType.OBJECT).description("장바구니에 담긴 제품 데이터"),
                                        fieldWithPath("cartDetail.cartDetailId").type(JsonFieldType.NUMBER).description("장바구니 제품 식별자"),
                                        fieldWithPath("cartDetail.count").type(JsonFieldType.NUMBER).description("제품 변경 수량")
                                )
                        )));
    }

    @Test
    @DisplayName("장바구니 제품 조회 API 문서화")
    void viewCartProduct() throws Exception {
        Long memberId = 1L;
        CartDetailDto response1 = new CartDetailDto(1L, "카레라면", 20000, 3);
        CartDetailDto response2 = new CartDetailDto(2L, "가지튀김", 10000, 5);
        List<CartDetailDto> response = new ArrayList<>();
        response.add(response1);
        response.add(response2);

        given(cartService.listCart(anyString())).willReturn(response);

        ResultActions actions = mockMvc.perform(
                get("/{member-id}/cart", memberId));

        actions.andExpect(status().isOk())
                .andDo(document("get-cart-products",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("cartProducts").type(JsonFieldType.ARRAY).description("전체 장바구니 제품 데이터"),
                                        fieldWithPath("cartProducts[].cartDetailId").type(JsonFieldType.NUMBER).description("제품 식별자"),
                                        fieldWithPath("cartProducts[].productName").type(JsonFieldType.STRING).description("제품명"),
                                        fieldWithPath("cartProducts[].price").type(JsonFieldType.NUMBER).description("제품 가격"),
                                        fieldWithPath("cartProducts[].count").type(JsonFieldType.NUMBER).description("구매 수량")
                                )
                        )));
    }

    @Test
    @DisplayName("장바구니 제품 삭제 API 문서화")
    void deleteCartProduct() throws Exception {
        Long memberId = 1L;
        Long cartDetailId = 2L;

        doNothing().when(cartService).deleteCart(anyLong());

        ResultActions actions = mockMvc.perform(
                delete("/{member-id}/cart/{cartDetail-id}", memberId, cartDetailId));

        actions.andExpect(status().isOk())
                .andDo(document("delete-cart-product",
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자"),
                                parameterWithName("cartDetail-id").description("장바구니 제품 식별자")
                        )));
    }
}
