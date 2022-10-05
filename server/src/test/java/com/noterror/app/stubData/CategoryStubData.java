package com.noterror.app.stubData;

import com.noterror.app.api.domain.category.dto.CategoryResponseDto;
import com.noterror.app.api.domain.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryStubData {
    public static CategoryResponseDto responseCategoryData() {
        return new CategoryResponseDto(
                1L,
                "라면류",
                2L,
                null
        );
    }
}
