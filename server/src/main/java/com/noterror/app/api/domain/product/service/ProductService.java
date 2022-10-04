package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;
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
     * @method 제품 전체 조회 기능
     * @param page
     * @param size
     * @param sort
     * @param orderBy
     * @return Page
     */
    Page<Product> findProductsWithPageAndSort(int page, int size, String sort, String orderBy);

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

    Product findExistProduct(Long productId);
}
