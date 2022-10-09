package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProductRepositoryExtension {

    Page<Product> findByVegetarianType(List<String> vegetarian, Pageable pageable);
}
