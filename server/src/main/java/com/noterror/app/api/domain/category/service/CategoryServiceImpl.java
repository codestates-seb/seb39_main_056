package com.noterror.app.api.domain.category.service;
import com.noterror.app.api.domain.category.dto.CategoryResponseDto;
import com.noterror.app.api.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> findCategoryList() {
        List<CategoryResponseDto> results = categoryRepository.findByDepth(1L).stream().map(CategoryResponseDto::of).collect(Collectors.toList());
    return results;
    }
}
