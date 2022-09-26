package com.noterror.app.api.domain.product.controller;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.dto.ProductPatchDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.mapper.ProductMapper;
import com.noterror.app.api.domain.product.service.ProductService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.time.LocalDateTime;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class ProductControllerRestDocs {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper mapper;

    @Test
    @DisplayName("제품 수정 API 문서화")
    void patchProductTest() throws Exception {
        Long productId = 1L;
        ProductPatchDto request = new ProductPatchDto(
                productId,
                "카레라면",
                20000,
                3,
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA",
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA");

        String content = gson.toJson(request);

        ProductResponseDto response = new ProductResponseDto(
                1L,
                "카레라면",
                20000,
                3,
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA",
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA",
                LocalDateTime.now());

        //given
        given(mapper.productPatchDtoToProduct(Mockito.any(ProductPatchDto.class))).willReturn(new Product());
        given(productService.updateProduct(Mockito.any(Product.class))).willReturn(new Product());
        given(mapper.productToProductResponseDto(Mockito.any(Product.class))).willReturn(response);

        //when
        ResultActions actions = mockMvc.perform(
                put("/products/admin/edit/{product-id}", response.getProductId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        //then
        actions.andExpect(status().isOk())
                .andDo(document("update-product",              // (9)
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                        parameterWithName("product-id").description("제품 식별자")
                ),
                requestFields(
                        List.of(
                                fieldWithPath("productName").type(JsonFieldType.STRING).description("제품명").optional(),
                                fieldWithPath("price").type(JsonFieldType.NUMBER).description("제품가격").optional(),
                                fieldWithPath("quantity").type(JsonFieldType.NUMBER).description("수량").optional(),
                                fieldWithPath("thumbnailImage").type(JsonFieldType.STRING).description("썸네일 이미지").optional(),
                                fieldWithPath("detailImage").type(JsonFieldType.STRING).description("상세 정보 이미지").optional()
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
                )));
    }
}

