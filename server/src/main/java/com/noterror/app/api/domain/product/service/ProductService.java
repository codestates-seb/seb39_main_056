package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.entity.Product;

public interface ProductService {

    Product createProduct(ProductRequestDto productRequestDto);
    Product updateProduct(ProductResponseDto productResponseDto);

    void delete(long productId);
}
