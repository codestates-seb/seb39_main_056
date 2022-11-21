package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.mapper.ProductMapper;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import com.noterror.app.api.global.exception.BusinessLogicException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.noterror.app.stubData.ProductStubData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

/**
 * 슬라이스 테스트
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceImplSliceTest {

    @Mock private ProductRepository productRepository;
    @Mock private ProductMapper mapper;

    @InjectMocks private ProductServiceImpl productService;

    @Test
    @DisplayName("제품 조회 성공- 데이터 존재")
    void findProduct_success_test() throws Exception {
        //when
        given(productRepository.findById(anyLong()))
                .willReturn(Optional.of(getProduct_1()));
        given(mapper.productToProductResponseDto(any(Product.class)))
                .willReturn(responseProductData());

        ProductResponseDto response = productService.findProduct(1L);

        //then
        assertEquals(response.getProductId(),getProduct_1().getProductId());
        assertEquals(response.getProductName(), getProduct_1().getProductName());
    }

    @Test
    @DisplayName("제품 조회 실패 - 조회된 제품이 없습니다.")
    void findExistProduct_fail_test() throws Exception {
        //given
        Product nullDataInDB = null;

        //when
        given(productRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(nullDataInDB));

        String result = "";
        try {
            productService.findExistProduct(1L);
        } catch (BusinessLogicException e) {
            result = e.getMessage();
        }

        //then
        assertTrue(result.equals("조회된 제품이 없습니다."));
    }

    @Test
    @DisplayName("제품 등록 성공")
    void createProduct_success_test() throws Exception {
        //given
        given(mapper.productRequestDtoToProduct(any(ProductRequestDto.class)))
                .willReturn(getProduct_1());
        given(productRepository.save(any(Product.class)))
                .willReturn(getProduct_1());
        given(mapper.productToProductResponseDto(any(Product.class)))
                .willReturn(responseProductData());
        //when
        ProductResponseDto response = productService.createProduct(requestProductData());

        //then
        assertThat(response.getProductName()).isEqualTo(requestProductData().getProductName());
    }
}