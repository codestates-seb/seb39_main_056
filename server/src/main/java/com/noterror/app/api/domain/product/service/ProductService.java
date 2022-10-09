package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.product.dto.QueryParamDto;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import org.springframework.data.domain.Page;

/**
 * @Interface 제품 CRUD 서비스
 */
public interface ProductService {

    /**
     * @Method 제품 상세 조회 기능
     * @param id
     * @return Product
     */
    ProductResponseDto findProduct(Long id);

    /**
     * @CASE1 인증되지 않은 유저일 때
     * @method 제품 전체 조회 기능
     * @param queryParamDto
     * @return Page
     */
    Page<Product> findProductsWhenAnonymous(QueryParamDto queryParamDto);

    /**
     * @CASE1 인증된 회원일 때
     * @method 제품 전체 조회 기능
     * @param queryParamDto
     * @return Page
     */
    Page<Product> findProductsWhenAuthenticated(QueryParamDto queryParamDto);

    /**
     * @Method 제품 등록 기능
     * @param request
     * @return ProductResponseDto
     */
    ProductResponseDto createProduct(ProductRequestDto request);

    /**
     * 제품 수정
     * @param id
     * @param request
     * @return productResponseDto
     */
    ProductResponseDto updateProduct(Long id, ProductRequestDto request);

    /**
     * 제품 삭제
     * @param id
     */
    void removeProduct(long id);
}
