package com.noterror.app.api.domain.product.controller;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.service.ProductService;
import com.noterror.app.api.global.response.SingleProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 제품 개별 조회
     * @param : productId
     */
    @GetMapping("/detail/{product-id}")
    public ResponseEntity getProduct(@PathVariable("product-id") Long productId) {

        Product getProduct = productService.findProduct(productId);

        return new ResponseEntity(
                new SingleProductResponse(getProduct),
                HttpStatus.OK);
    }
}
