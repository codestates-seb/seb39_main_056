package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
