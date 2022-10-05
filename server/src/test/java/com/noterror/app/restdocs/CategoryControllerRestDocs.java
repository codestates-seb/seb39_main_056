//package com.noterror.app.restdocs;
//
//import com.noterror.app.api.domain.category.controller.CategoryController;
//import com.noterror.app.api.domain.category.dto.CategoryResponseDto;
//import com.noterror.app.api.domain.category.service.CategoryService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static com.noterror.app.stubData.CategoryStubData.responseCategoryData;
//
//@WebMvcTest(CategoryController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class CategoryControllerRestDocs {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CategoryService categoryService;
//
//    @Test
//    @DisplayName("카테고리 전체 조회 API 문서화")
//    void getCategoryList() throws Exception {
//        given(categoryService.findCategoryList())
//                .willReturn((List<CategoryResponseDto>) responseCategoryData());
//
//        mockMvc.perform(
//                        get("/", responseCategoryData()))
//                .andExpect(status().isOk())
//                .andDo(document("get-category",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("category").type(JsonFieldType.OBJECT).description("카테고리 결과 데이터"),
//                                        fieldWithPath("category.categoryId").type(JsonFieldType.NUMBER).description("카테고리 ID"),
//                                        fieldWithPath("category.categoryName").type(JsonFieldType.STRING).description("카테고리명"),
//                                        fieldWithPath("category.depth").type(JsonFieldType.NUMBER).description("카테고리 차수"),
//                                        fieldWithPath("category.children").type(JsonFieldType.ARRAY).description("하위 카테고리")
//                                )
//                        )
//                ));
//
//    }
//}
