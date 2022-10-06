package com.noterror.app.api.domain.category.controller;

import com.noterror.app.api.domain.category.dto.CategoryResponseDto;
import com.noterror.app.api.domain.category.service.CategoryService;
import com.noterror.app.api.global.response.SingleCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 카테고리 전체조회
     */
    @GetMapping
    public ResponseEntity getCategoryList() {
        List <CategoryResponseDto> response = categoryService.findCategoryList();
        return new ResponseEntity(
                new SingleCategoryResponse(response),
                HttpStatus.OK);
    }
}
