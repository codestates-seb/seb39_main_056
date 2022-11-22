package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.product.repository.ProductRepository;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.global.exception.BusinessLogicException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


/**
 * 핵심 기능 슬라이스 테스트
 */
@ExtendWith(MockitoExtension.class)
class findProductsSliceTest {

    @Mock private ProductRepository productRepository;

    @InjectMocks private ProductServiceImpl productService;

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
}
