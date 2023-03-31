package com.noterror.app.api.category.dto;

import com.noterror.app.api.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {
    private Long categoryId;
    private String categoryName;
    private Long depth;
    private List<CategoryResponseDto> children;

    /**
     * 카테고리를 전달할 DTO 정의한 정적 메서드
     */
    public static CategoryResponseDto of(Category category) {
        return new CategoryResponseDto(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getDepth(),
                category.getChildren().stream().map(CategoryResponseDto::of).collect(Collectors.toList())
        );
    }
}
