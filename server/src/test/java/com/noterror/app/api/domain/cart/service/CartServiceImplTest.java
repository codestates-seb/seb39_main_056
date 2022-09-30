package com.noterror.app.api.domain.cart.service;

import com.noterror.app.api.domain.cart.dto.CartProductDto;
import com.noterror.app.api.domain.cart.repository.CartDetailRepository;
import com.noterror.app.api.domain.cart.repository.CartRepository;
import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.entity.Role;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceImplTest {

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Autowired
    CartServiceImpl service;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductRepository productRepository;


    @Test
    void addCart() {

        memberRepository.save(new Member(1L, "kang","email@naver.com","01011111111", "12345","제주시","아파트"," ", LocalDateTime.now(),null, Role.USER);
        Long memberId = 1L;
        Long productId = 3L;
        CartProductDto cartProductDto = new CartProductDto

        service.addCart(cartProductDto, memberId);
    }
}