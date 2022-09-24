package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;

public interface ProductService {

    /**
     * 제품 서비스
     * @method findProduct : 제품 조회 기능
     * @return Product
     */
    Product findProduct(Long productId);
}
