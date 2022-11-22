package com.noterror.app.api.domain.cart.service;

import com.noterror.app.api.domain.cart.repository.CartDetailRepository;
import com.noterror.app.api.domain.cart.repository.CartRepository;
import com.noterror.app.api.entity.cart.CartDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CartServiceSliceTest {

    @Mock private CartRepository cartRepository;
    @Mock private CartDetailRepository cartDetailRepository;

    @InjectMocks private CartServiceImpl cartService;

    @Test
    @DisplayName("장바구니에 추가하려는 제품이 이미 존재할 경우 - 장바구니 안에 제품 수량만 변경")
    void checkAlreadyExistProductTest() throws Exception {

        //given

        //when

        //then

    }
}
