package com.noterror.app.api.domain.category.service;

import com.noterror.app.api.domain.category.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    /**
     *
     * @return
     */
    List<CategoryResponseDto> findCategoryList();
}
