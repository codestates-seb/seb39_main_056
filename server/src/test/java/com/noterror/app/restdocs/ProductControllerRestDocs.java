package com.noterror.app.restdocs;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.controller.ProductController;
import com.noterror.app.api.domain.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureRestDocs
class ProductControllerRestDocs {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getProduct() throws Exception {

        Product productData
                = Product.builder()
                .productId(1L)
                .productName("카레라면")
                .price(10000)
                .quantity(3)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .signDate(LocalDateTime.now())
                .ingredients(List.of("야채류","해조류","균류","어패류","난류"))
                .categories(List.of("간편식","조미용식"))
                .build();

        given(productService.findProduct(Mockito.anyLong())).willReturn(productData);

        mockMvc.perform(
                get("/product/detail/{product-id}"
                        ,productData.getProductId()))
                .andExpect(status().isOk())
                .andDo(document("get-product",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("product-id").description("제품 식별자")
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
                                        fieldWithPath("product.signDate").type(JsonFieldType.VARIES).description("제품 등록 날짜"),
                                        fieldWithPath("product.ingredients").type(JsonFieldType.ARRAY).description("식재료 정보"),
                                        fieldWithPath("product.categories").type(JsonFieldType.ARRAY).description("카테고리")
                                )
                        )
                        ));
    }
}