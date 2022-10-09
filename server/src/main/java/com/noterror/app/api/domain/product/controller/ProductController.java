package com.noterror.app.api.domain.product.controller;

import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.dto.QueryParamDto;
import com.noterror.app.api.domain.product.service.ProductService;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.global.response.MultiProductsResponse;
import com.noterror.app.api.global.response.SingleProductResponse;
import com.noterror.app.api.global.sort.Sort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 담당자 : 강시혁, 황윤준
 * SCOPE : 제품 조회
 * 리팩토링 : 강시혁
 * 대상 : PRODUCTS
 */
@RestController
@CrossOrigin
@Slf4j
@Validated
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 제품 개별 조회
     * (인증 필요없음)
     */
    @GetMapping("/detail/{product-id}")
    public ResponseEntity getProduct(@PathVariable("product-id") Long productId) {
        ProductResponseDto response = productService.findProduct(productId);
        return new ResponseEntity(new SingleProductResponse(response), HttpStatus.OK);
    }

    /**
     * 제품 전체 조회
     *
     * @CASE1:인증되지 않은 유저일 때 : "프렉시테리언" 유형을 기본으로 전체 제품 출력
     * @CASE2:인증O : 인증된 회원의 유형에 맞게 전체 제품 출력
     */
    @GetMapping("/list")
    public ResponseEntity getProducts(@RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "20") int size,
                                      @RequestParam(required = false, defaultValue = "createDate") String sort,
                                      @RequestParam(required = false, defaultValue = "desc") String orderBy,
                                      @RequestParam(required = false, defaultValue = "플렉시테리언", value = "vegetarianTypeName") String vegetarianTypeName) {

        QueryParamDto queryParamDto = new QueryParamDto(page, size, sort, orderBy, vegetarianTypeName);
        String currentUserEmail = getCurrentUserEmail();

        Page<Product> productsInPage = findProducts(queryParamDto, currentUserEmail);
        List<Product> productsInList = productsInPage.getContent();
        Sort sortInfo = new Sort(sort, orderBy);

        List<ProductResponseDto> products = productsInPage.stream().map(ProductResponseDto::new).collect(Collectors.toList());

        return new ResponseEntity(
                new MultiProductsResponse(products, productsInPage, sortInfo), HttpStatus.OK);
    }

    private Page<Product> findProducts(QueryParamDto queryParamDto, String email) {
        if (isAnonymousUser(email)) {
            return productService.findProductsWhenAnonymous(queryParamDto);
        }
        return productService.findProductsWhenAuthenticated(queryParamDto);
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private boolean isAnonymousUser(String email) {
        return email.equals("anonymousUser");
    }
}
