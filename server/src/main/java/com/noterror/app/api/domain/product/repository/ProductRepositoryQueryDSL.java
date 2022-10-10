package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepositoryQueryDSL {

    List<Long> findProductIdByVegetarianType(String type);

    Page<Product> findProductById(List<Long> productIds, Pageable pageable);
}
