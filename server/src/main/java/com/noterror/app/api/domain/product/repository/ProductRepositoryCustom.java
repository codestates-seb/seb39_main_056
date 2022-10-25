package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ProductRepositoryCustom {

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE vegetarian_type IN " +
            "(SELECT vegetarian_type FROM vegetarian WHERE levels " +
            "< (SELECT levels FROM vegetarian WHERE vegetarian_type = :vegetarianType) " +
            "OR vegetarian_type = :vegetarianType)")
    Page<Product> findAllByVegetarian(@Param("vegetarianType") String vegetarianType, Pageable pageable);
}
