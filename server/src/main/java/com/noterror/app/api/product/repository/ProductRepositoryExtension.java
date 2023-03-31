package com.noterror.app.api.product.repository;

import com.noterror.app.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProductRepositoryExtension {

    Page<Product> findAllByVegetarian(String vegetarianType, Pageable pageable);
}
