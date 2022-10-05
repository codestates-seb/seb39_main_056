package com.noterror.app.stubData;

import com.noterror.app.api.domain.category.dto.CategoryResponseDto;

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
