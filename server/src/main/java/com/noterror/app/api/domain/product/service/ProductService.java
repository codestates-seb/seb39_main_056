package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    Product updateProduct(Product product);

    Product findExistProduct(Long productId);
}
