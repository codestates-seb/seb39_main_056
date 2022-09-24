package com.noterror.app.api.domain.product.controller;

import static org.junit.jupiter.api.Assertions.*;import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.controller.ProductController;
import com.noterror.app.api.domain.product.service.ProductService;
import com.noterror.app.api.global.response.SingleProductResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class) //(1)
@AutoConfigureRestDocs //(2)
class ProductControllerRestDocs {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("제품 수정 API 문서화")
    void patchProductTest() throws Exception {
        Product productData = Product.builder()
                .productId(1L)
                .productName("카레라면")
                .price(10000)
                .quantity(3)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .signDate(LocalDateTime.now())
                .build();

        //given
        given(productService.updateProduct(Mockito.anyLong())).willReturn(productData);

        //when
        ResultActions actions = mockMvc.perform(
                put("/admin/edit/{product-id}", productData.getProductId())
                        .content(productData.toString())
                        .contentType(MediaType.APPLICATION_JSON));


        actions
                        .andExpect(status().isOk())
                        .andDo(document("put-product",              // (9)
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("product-id").description("제품 식별자")
                             ),
                            requestFields(
                                 List.of(fieldWithPath("product").type(JsonFieldType.OBJECT).description("제품 결과 데이터"),
                                         fieldWithPath("product.productName").type(JsonFieldType.STRING).description("제품명").optional(),
                                         fieldWithPath("product.price").type(JsonFieldType.NUMBER).description("제품가격").optional(),
                                         fieldWithPath("product.quantity").type(JsonFieldType.NUMBER).description("수량").optional(),
                                         fieldWithPath("product.thumbnailImage").type(JsonFieldType.STRING).description("썸네일 이미지").optional(),
                                         fieldWithPath("product.detailImage").type(JsonFieldType.STRING).description("상세 정보 이미지").optional()
                                 )
                                ),
                            responseFields(
                                List.of(
                                        fieldWithPath("product").type(JsonFieldType.OBJECT).description("제품 결과 데이터"),
                                        fieldWithPath("product.productId").type(JsonFieldType.NUMBER).description("제품 식별자"),
                                        fieldWithPath("product.productName").type(JsonFieldType.STRING).description("제품명"),
                                        fieldWithPath("product.price").type(JsonFieldType.NUMBER).description("제품가격"),
                                        fieldWithPath("product.quantity").type(JsonFieldType.NUMBER).description("수량"),
                                        fieldWithPath("product.thumbnailImage").type(JsonFieldType.STRING).description("썸네일 이미지"),
                                        fieldWithPath("product.detailImage").type(JsonFieldType.STRING).description("상세 정보 이미지"),
                                        fieldWithPath("product.signDate").type(JsonFieldType.VARIES).description("제품 등록 날짜")
                                )
                        )
                ));
    }
}
