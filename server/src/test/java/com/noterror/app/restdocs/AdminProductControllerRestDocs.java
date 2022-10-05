package com.noterror.app.restdocs;

import com.google.gson.Gson;
import com.noterror.app.api.domain.admin.AdminProductController;
import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.service.ProductService;
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

import static com.noterror.app.stubData.ProductStubData.requestProductData;
import static com.noterror.app.stubData.ProductStubData.responseProductData;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminProductController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class AdminProductControllerRestDocs {

    @Autowired MockMvc mockMvc;
    @Autowired Gson gson;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("제품 등록 API 문서화")
    void postProductApiDocs() throws Exception {

        String jsonOfRequest = gson.toJson(requestProductData());

        given(
                productService.createProduct(
                        Mockito.any(ProductRequestDto.class)))
                .willReturn(responseProductData());

        ResultActions action = mockMvc.perform(
                post("/admin/products/registration")
                        .accept(MediaType.APPLICATION_JSON)    //APPLICATION_JSON 형식으로 보내겠다.
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonOfRequest));

        action.andExpect(status().isCreated())
                .andExpect(jsonPath("$.product.productName").value("카레라면"))
                .andDo(document("post-product",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("productName").type(JsonFieldType.STRING).description("제품명"),
                                        fieldWithPath("price").type(JsonFieldType.NUMBER).description("제품가격"),
                                        fieldWithPath("quantity").type(JsonFieldType.NUMBER).description("수량"),
                                        fieldWithPath("thumbnailImage").type(JsonFieldType.STRING).description("썸네일 이미지"),
                                        fieldWithPath("detailImage").type(JsonFieldType.STRING).description("상세 정보 이미지")
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

    @Test
    @DisplayName("제품 수정 API 문서화")
    void putProductApiDocs() throws Exception {

        Long productId = 1L;

        String jsonOfRequest = gson.toJson(requestProductData());

        given(
                productService.updateProduct(
                        anyLong(),
                        Mockito.any(ProductRequestDto.class)))
                .willReturn(responseProductData());

        ResultActions actions = mockMvc.perform(
                put("/admin/products/edit/{product-id}", productId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonOfRequest));

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

    @Test
    @DisplayName("제품 삭제 API 문서화")
    void deleteProductApiDocs() throws Exception {

        Long productId = 1L;

        doNothing().when(productService).removeProduct(anyLong());

        mockMvc.perform(
                        delete("/admin/products/edit/{product-id}", productId))
                .andExpect(status().isNoContent())
                .andDo(document("delete-product",
                        pathParameters(
                                parameterWithName("product-id").description("제품 식별자")
                        )
                ));
    }
}