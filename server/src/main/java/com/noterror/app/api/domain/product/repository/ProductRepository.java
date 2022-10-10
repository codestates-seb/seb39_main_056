package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.VegetarianType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE vegetarian_type_name IN " +
            "(SELECT vegetarian_type_name FROM vegetarian_type WHERE vegetarian_type_level " +
            "< (SELECT vegetarian_type_level FROM vegetarian_type WHERE vegetarian_type_name = :vegetarianTypeName) " +
            "OR vegetarian_type_name = :vegetarianTypeName)")
    Page<Product> findAllByVegetarianTypeName(PageRequest of, @Param("vegetarianTypeName") String vegetarianTypeName);

}