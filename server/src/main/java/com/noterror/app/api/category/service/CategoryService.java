package com.noterror.app.api.category.service;

import com.noterror.app.api.category.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    /**
     * @return
     */
    List<CategoryResponseDto> findCategoryList();
}
