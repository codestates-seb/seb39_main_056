package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = "select * from product where " +
            "(select * from vegetarian_type where vegetarian_type_level < " +
            "(select vegetarian_type_level from vegetarian_type where vegetarian_type_name = :vegetarianTypeName) " +
            "or vegetarian_type_name = :vegetarianTypeName) order by create_date desc")
    Page<Product> findAllByVegetarianType(PageRequest of, @Param("vegetarianTypeName") String vegetarianTypeName);

}
