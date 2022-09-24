package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    @Override
    public Product updateProduct(Long productId){
        return null;
    }
}
