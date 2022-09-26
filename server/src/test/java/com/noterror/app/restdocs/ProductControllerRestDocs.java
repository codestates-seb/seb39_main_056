package com.noterror.app.restdocs;

import com.noterror.app.api.admin.AdminProductController;
import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.controller.ProductController;
import com.noterror.app.api.domain.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.noterror.app.stubData.ProductStubData.getProductsSortSignDate;
import static com.noterror.app.stubData.ProductStubData.responseProductData;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class ProductControllerRestDocs {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("제품 상세 조회 API 문서화")
    void getProduct() throws Exception {

        given(productService.findProduct(Mockito.anyLong()))
                .willReturn(responseProductData());

        mockMvc.perform(
                        get("/products/detail/{product-id}"
                                , responseProductData().getProductId()))
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
                                        fieldWithPath("product.signDate").type(JsonFieldType.VARIES).description("제품 등록 날짜")
                                        //fieldWithPath("product.ingredients").type(JsonFieldType.ARRAY).description("식재료 정보"),
                                        //fieldWithPath("product.categories").type(JsonFieldType.ARRAY).description("카테고리")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("제품 전체 조회 API 문서화")
    void getProducts_with_page_and_size() throws Exception {

        Page<Product> responseProductsInPage =
                new PageImpl(getProductsSortSignDate());

        given(productService.findProductsWithPageAndSort(anyInt(), anyInt(), anyString(), anyString()))
                .willReturn(responseProductsInPage);

        mockMvc.perform(
                        get("/products/list")
                                .param("page", "1")
                                .param("size", "20")
                                .param("sort", "signDate")
                                .param("orderBy", "desc"))
                .andExpect(status().isOk())
                .andDo(document("get-products",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("Page 번호").optional(),
                                        parameterWithName("size").description("Page 내의 데이터 개수").optional(),
                                        parameterWithName("sort").description("정렬 기준").optional(),
                                        parameterWithName("orderBy").description("정렬 방식").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("products").type(JsonFieldType.ARRAY).description("전체 제품 결과 데이터"),
                                        fieldWithPath("products[].productId").type(JsonFieldType.NUMBER).description("제품 식별자"),
                                        fieldWithPath("products[].productName").type(JsonFieldType.STRING).description("제품명"),
                                        fieldWithPath("products[].price").type(JsonFieldType.NUMBER).description("제품가격"),
                                        fieldWithPath("products[].quantity").type(JsonFieldType.NUMBER).description("수량"),
                                        fieldWithPath("products[].thumbnailImage").type(JsonFieldType.STRING).description("썸네일 이미지"),
                                        fieldWithPath("products[].detailImage").type(JsonFieldType.STRING).description("상세 정보 이미지"),
                                        fieldWithPath("products[].signDate").type(JsonFieldType.VARIES).description("제품 등록 날짜"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보").optional(),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 번호").optional(),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 사이즈").optional(),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 건 수").optional(),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수").optional(),
                                        fieldWithPath("sizeInfo").type(JsonFieldType.OBJECT).description("정렬 정보").optional(),
                                        fieldWithPath("sortInfo.sort").type(JsonFieldType.STRING).description("정렬 기준").optional(),
                                        fieldWithPath("sortInfo.orderBy").type(JsonFieldType.STRING).description("정렬 방식").optional()
                                )
                        )
                ));
    }


}
