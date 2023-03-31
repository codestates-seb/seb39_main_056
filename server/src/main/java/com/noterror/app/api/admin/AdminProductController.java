package com.noterror.app.api.admin;

import com.noterror.app.api.product.dto.ProductRequestDto;
import com.noterror.app.api.product.dto.ProductResponseDto;
import com.noterror.app.api.product.service.ProductService;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.global.response.SingleProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * SCOPE : ADMIN PAGE, ROLE_ADMIN
 */
@RestController
@Validated
@Slf4j
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    /**
     * 제품 등록
     *
     * @RequestBody 제품명, 수량, 금액, 썸네일 이미지, 상세 이미지
     */
    @PostMapping("/registration")
    public ResponseEntity<ProductResponseDto> postProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Product product = productRequestDto.toEntity();
        Product newProduct = productService.createProduct(product);
        ProductResponseDto response = new ProductResponseDto(newProduct);

        return new ResponseEntity(
                new SingleProductResponse(response), HttpStatus.CREATED);
    }

    /**
     * 제품 수정
     *
     * @PathVariable 제품 식별자
     * @RequestBody 제품명, 수량, 금액, 썸네일 이미지, 상세 이미지
     */
    @PutMapping("/edit/{product-id}")
    public ResponseEntity putProduct(@PathVariable("product-id") Long productId,
                                     @Valid @RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestDto.toEntity();
        Product updateProduct = productService.updateProduct(productId, product);
        ProductResponseDto response = new ProductResponseDto(updateProduct);

        return new ResponseEntity<>(
                new SingleProductResponse<>(response), HttpStatus.OK);
    }

    /**
     * 제품 삭제
     */
    @DeleteMapping("/edit/{product-id}")
    public ResponseEntity deleteProduct(@PathVariable("product-id") Long productId) {
        productService.removeProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
