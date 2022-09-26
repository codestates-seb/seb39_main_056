package com.noterror.app.api.domain.product.controller;

import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.entity.Product;
import com.noterror.app.api.domain.product.service.ProductService;
import com.noterror.app.api.global.response.SingleProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin/registration")
    public ResponseEntity<ProductResponseDto> postProduct(@RequestBody ProductRequestDto productRequestDto){
        Product sellerProductRequest = productService.createProduct(productRequestDto);

        return new ResponseEntity(
                new SingleProductResponse(new ProductResponseDto(sellerProductRequest)), HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/edit/{productId}")
    public ResponseEntity deleteProduct(@PathVariable("productId") long ProductId) {
        productService.delete(ProductId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
