package com.noterror.app.api.category.service;

import com.noterror.app.api.category.dto.CategoryResponseDto;
import com.noterror.app.api.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> findCategoryList() {
        List<CategoryResponseDto> results = categoryRepository.findByDepth(1L).stream().map(CategoryResponseDto::of).collect(Collectors.toList());
        return results;
    }
}
