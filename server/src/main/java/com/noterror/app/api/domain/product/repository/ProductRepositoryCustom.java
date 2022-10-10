package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;

public interface ProductRepositoryCustom {

    Page<Product> findAllByVegetarianType(PageRequest of, @Param("vegetarianTypeName") String vegetarianTypeName);
}
