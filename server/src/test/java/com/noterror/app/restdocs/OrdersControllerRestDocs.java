/*
package com.noterror.app.restdocs;

import com.google.gson.Gson;
import com.noterror.app.api.domain.orders.controller.OrdersController;
import com.noterror.app.api.domain.orders.dto.OrderDto;
import com.noterror.app.api.domain.orders.dto.OrderInfoDto;
import com.noterror.app.api.domain.orders.dto.OrderProductDto;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import com.noterror.app.api.domain.orders.service.OrdersService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static com.noterror.app.api.entity.order.OrdersStatus.ORDER_REQUEST;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrdersController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class OrdersControllerRestDocs {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Gson gson;

    @MockBean
    private OrdersService ordersService;

    @Test
    @DisplayName("주문 제품 등록 API 문서화")
    void postOrderProductApiDocs() throws Exception{
        Long memberId = 1L;
        OrderDto request = new OrderDto(1L, 5);
        String jsonRequest = gson.toJson(request);

        OrderProductDto orderProductList1 = new OrderProductDto(1L,"호랑이라면",5,1500);
        OrderProductDto orderProductList2 = new OrderProductDto(2L,"고양이라면",10,100);

        OrderResponseDto response = new OrderResponseDto(1L, ORDER_REQUEST,LocalDateTime.now(),50000, List.of(orderProductList1, orderProductList2));
        given(ordersService.orderProduct(any(),anyLong())).willReturn(response);

        ResultActions action = mockMvc.perform(
                post("/{member-id}/orders", memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest));

        action.andExpect(status().isOk())
                .andDo(document("post-Order",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("productId").type(JsonFieldType.NUMBER).description("제품 식별자"),
                                        fieldWithPath("quantity").type(JsonFieldType.NUMBER).description("제품 수량")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("orders").type(JsonFieldType.OBJECT).description("개별 주문 데이터"),
                                        fieldWithPath("orders.ordersId").type(JsonFieldType.NUMBER).description("개별 주문 식별자"),
                                        fieldWithPath("orders.ordersStatus").type(JsonFieldType.STRING).description("개별 주문 상태"),
                                        fieldWithPath("orders.ordersDate").type(JsonFieldType.VARIES).description("개별 주문 등록 시간"),
                                        fieldWithPath("orders.totalPrice").type(JsonFieldType.NUMBER).description("개별 주문의 총 가격"),
                                        fieldWithPath("orders.orderProductList").type(JsonFieldType.ARRAY).description("개별 주문에 담긴 개별 재품 데이터"),
                                        fieldWithPath("orders.orderProductList[].productId").type(JsonFieldType.NUMBER).description("개별 주문에 담긴 개별 제품 식별자"),
                                        fieldWithPath("orders.orderProductList[].productName").type(JsonFieldType.STRING).description("개별 주문에 담긴 개별 제품 이름"),
                                        fieldWithPath("orders.orderProductList[].quantity").type(JsonFieldType.NUMBER).description("개별 주문에 담긴 개별 제품 수량"),
                                        fieldWithPath("orders.orderProductList[].productPrice").type(JsonFieldType.NUMBER).description("개별 주문에 담긴 제별 제품 가격")
                                )
                        )));
    }

    @Test
    @DisplayName("주문 제품 조회 API 문서화")
    void getOrdersProduct() throws Exception{
        Long memberId = 1L;
        OrderProductDto orderProductList1 = new OrderProductDto(1L,"호랑이라면",5,1500);
        OrderProductDto orderProductList2 = new OrderProductDto(2L,"고양이라면",10,100);
        OrderProductDto orderProductList3 = new OrderProductDto(3L,"코끼리라면",1,5000);

        OrderInfoDto infoDto1 = new OrderInfoDto(1L, LocalDateTime.now(), ORDER_REQUEST, 8500, List.of(orderProductList1,orderProductList2));
        OrderInfoDto infoDto2 = new OrderInfoDto(2L, LocalDateTime.now(), ORDER_REQUEST, 6000, List.of(orderProductList2,orderProductList3));

        List<OrderInfoDto> response = new ArrayList<>();
        response.add(infoDto1);
        response.add(infoDto2);
        given(ordersService.getOrderList(anyLong())).willReturn(response);

        ResultActions actions = mockMvc.perform(
                get("/{member-id}/orders", memberId));

        actions.andExpect(status().isOk())
                .andDo(document("get-orders-products",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("orders").type(JsonFieldType.ARRAY).description("전체 주문 데이터"),
                                        fieldWithPath("orders[].orderId").type(JsonFieldType.NUMBER).description("전체 주문의 개별 주문 식별자"),
                                        fieldWithPath("orders[].ordersStatus").type(JsonFieldType.VARIES).description("전체 주문의 개별 주문 등록시간 "),
                                        fieldWithPath("orders[].orderDate").type(JsonFieldType.STRING).description("전체 주문의 개별 주문 상태"),
                                        fieldWithPath("orders[].totalPrice").type(JsonFieldType.NUMBER).description("전체 주문의 개별 주문 총 가격"),
                                        fieldWithPath("orders[].orderProductList").type(JsonFieldType.ARRAY).description("전체 주문의 개별 주문 데이터"),
                                        fieldWithPath("orders[].orderProductList[].productId").type(JsonFieldType.NUMBER).description("전체 주문의 개별 주문의 상품 식별자"),
                                        fieldWithPath("orders[].orderProductList[].productName").type(JsonFieldType.STRING).description("전체 주문의 개별 주문의 상품 이름"),
                                        fieldWithPath("orders[].orderProductList[].quantity").type(JsonFieldType.NUMBER).description("전체 주문의 개별 주문의 상품 수량"),
                                        fieldWithPath("orders[].orderProductList[].productPrice").type(JsonFieldType.NUMBER).description("전체 주문의 개별 주문의 상품 가격")
                                        )
                                )));
    }
}
