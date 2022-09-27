package com.noterror.app.restdocs;

import com.google.gson.Gson;
import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.dto.ProductPatchDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class ProductControllerRestDocs {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("제품 상세 조회 API 문서화")
    void getProduct() throws Exception {

        Product productDataInDB
                = Product.builder()
                .productId(1L)
                .productName("카레라면")
                .price(10000)
                .quantity(3)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .signDate(LocalDateTime.now())
                //.ingredients(List.of("야채류","해조류","균류","어패류","난류"))
                //.categories(List.of("간편식","조미용식"))
                .build();

        given(productService.findProduct(Mockito.anyLong())).willReturn(productDataInDB);

        mockMvc.perform(
                get("/products/detail/{product-id}"
                        ,productDataInDB.getProductId()))
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

        Product productDataInDB_1
                = Product.builder()
                .productId(1L)
                .productName("카레라면")
                .price(10000)
                .quantity(3)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .signDate(LocalDateTime.now())
                .build();

        Product productDataInDB_2
                = Product.builder()
                .productId(2L)
                .productName("옥수수식빵")
                .price(2000)
                .quantity(2)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .signDate(LocalDateTime.now().plusDays(1))
                .build();

        List<Product> responseProductsInList = (List.of(productDataInDB_2, productDataInDB_1));
        Page<Product> responseProductsInPage = new PageImpl(responseProductsInList);

        given(productService.findProductsWithPageAndSort(anyInt(),anyInt(),anyString(),anyString()))
                .willReturn(responseProductsInPage);

        mockMvc.perform(
                get("/products/list")
                        .param("page","1")
                        .param("size","20")
                        .param("sort","signDate")
                        .param("orderBy","desc"))
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
    
    @Test
    @DisplayName("제품 등록 API 문서화")
    void postProduct() throws Exception {

        Product requestProductData
                = Product.builder()
                .productName("카레라면")
                .price(10000)
                .quantity(3)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .build();

        Product responseProductData
                = Product.builder()
                .productName("카레라면")
                .productId(1L)
                .price(10000)
                .quantity(3)
                .thumbnailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .detailImage("AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA")
                .signDate(LocalDateTime.now())
                .build();

        String content = gson.toJson(requestProductData);

        given(productService.createProduct(Mockito.any())).willReturn(responseProductData);


        ResultActions action = mockMvc.perform(
                post("/products/admin/registration")
                        .accept(MediaType.APPLICATION_JSON)    //APPLICATION_JSON 형식으로 보내겠다.
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        action.andExpect(status().isCreated())
                .andExpect(jsonPath("$.product.productName").value("카레라면"))//이름이 카레라면인지 확인해보기
                .andDo(document("post-product",              // 문서이름
                        preprocessRequest(prettyPrint()),          //이쁘게 json 선택
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
                LocalDateTime.now(),
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA",
                "AOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYAAOh-ky201T2iwWCIEQQOTQYxLJ90U01aMK7o8NrPzoCSYA"
                );

        //given
        given(productService.updateProduct(Mockito.any(ProductPatchDto.class))).willReturn(new Product());

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
