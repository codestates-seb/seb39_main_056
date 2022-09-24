package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Product findProduct(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(()-> new NullPointerException("조회된 제품이 없습니다."));
    }
}
