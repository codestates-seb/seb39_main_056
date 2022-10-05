package com.noterror.app.api.domain.category.repository;

import com.noterror.app.api.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByDepth(Long depth);
}
