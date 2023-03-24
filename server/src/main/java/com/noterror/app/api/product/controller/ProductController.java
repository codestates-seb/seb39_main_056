package com.noterror.app.api.product.controller;

import com.noterror.app.api.member.service.MemberService;
import com.noterror.app.api.product.dto.ProductResponseDto;
import com.noterror.app.api.product.dto.QueryParamDto;
import com.noterror.app.api.product.service.ProductService;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.response.MultiProductsResponse;
import com.noterror.app.api.global.response.SingleProductResponse;
import com.noterror.app.api.global.response.SortInfo;
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

@RestController
@Slf4j
@Validated
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final MemberService memberService;

    /**
     * 제품 개별 조회
     * (인증 필요없음)
     */
    @GetMapping("/detail/{product-id}")
    public ResponseEntity getProduct(@PathVariable("product-id") Long productId) {
        Product findProduct = productService.findProduct(productId);
        ProductResponseDto response = new ProductResponseDto(findProduct);
        return new ResponseEntity(new SingleProductResponse(response), HttpStatus.OK);
    }

    /**
     * 제품 전체 조회
     *
     * @CASE1:인증되지 않은 유저일 때 : "플렉시테리언" 유형을 기본으로 전체 제품 출력
     * @CASE2:인증O : 인증된 회원의 유형에 맞게 전체 제품 출력
     */
    @GetMapping("/list")
    public ResponseEntity getProducts(@RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "20") int size,
                                      @RequestParam(required = false, defaultValue = "create_date") String sort,
                                      @RequestParam(required = false, defaultValue = "desc") String orderBy,
                                      @RequestParam(required = false) String vegetarian) {

        QueryParamDto queryParamDto = new QueryParamDto(page - 1, size, sort, orderBy, vegetarian);
        Page<Product> productsInPage = findProducts(queryParamDto, getCurrentUserEmail());
        List<ProductResponseDto> response = toListOfProductResponses(productsInPage);

        return new ResponseEntity(
                new MultiProductsResponse(response, productsInPage, new SortInfo(sort, orderBy)),
                HttpStatus.OK
        );
    }

    private Page<Product> findProducts(QueryParamDto queryParamDto, String email) {
        if (isAnonymousUser(email)) {
            return productService.findProductsWhenAnonymous(queryParamDto);
        } else {
            return productService.findProductsWhenAuthenticated(queryParamDto, getMemberByEmail());
        }
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private boolean isAnonymousUser(String email) {
        return email.equals("anonymousUser");
    }

    private Member getMemberByEmail() {
        return memberService.findMemberByEmail(getCurrentUserEmail());
    }

    private List<ProductResponseDto> toListOfProductResponses(Page<Product> productsInPage) {
        return productsInPage.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }
}
