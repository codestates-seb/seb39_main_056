package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.product.dto.QueryParamDto;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.entity.member.Member;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Interface 제품 CRUD 서비스
 */
public interface ProductService {

    /**
     * @param id
     * @return Product
     * @Method 제품 상세 조회 기능
     */
    Product findProduct(Long id);

    /**
     * @param queryParamDto
     * @return Page
     * @CASE1 인증되지 않은 유저일 때
     * @method 제품 전체 조회 기능
     */
    Page<Product> findProductsWhenAnonymous(QueryParamDto queryParamDto);

    /**
     * @param queryParamDto
     * @return Page
     * @CASE1 인증된 회원일 때
     * @method 제품 전체 조회 기능
     */
    Page<Product> findProductsWhenAuthenticated(QueryParamDto queryParamDto, Member member);

    /**
     * @param product
     * @return ProductResponseDto
     * @Method 제품 등록 기능
     */
    Product createProduct(Product product);

    /**
     * 제품 수정
     *
     * @param id
     * @param product
     * @return productResponseDto
     */
    Product updateProduct(Long id, Product product);

    /**
     * 제품 삭제
     *
     * @param id
     */
    void removeProduct(Long id);
}
