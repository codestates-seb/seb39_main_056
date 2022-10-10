package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCustom {

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE vegetarian_type_name IN " +
            "(SELECT vegetarian_type_name FROM vegetarian_type WHERE vegetarian_type_level " +
            "< (SELECT vegetarian_type_level FROM vegetarian_type WHERE vegetarian_type_name = :vegetarianTypeName) " +
            "OR vegetarian_type_name = :vegetarianTypeName)")
    Page<Product> findAllByVegetarianTypeName(@Param("vegetarianTypeName") String vegetarianTypeName, Pageable pageable);
}
