package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    /**
     * 제품 서비스
     * @method findProduct : 제품 조회 기능
     * @return Product
     */
    Product findProduct(Long productId);

    /**
     * @method 제품 전체 조회 기능
     * @param page
     * @param size
     * @param sort
     * @param orderBy
     * @return Page
     */
    Page<Product> findProductsWithPageAndSort(int page, int size, String sort, String orderBy);

    List<Product> pageToListProducts(Page page);
}
