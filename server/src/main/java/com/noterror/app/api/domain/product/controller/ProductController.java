package com.noterror.app.api.domain.product.controller;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.service.ProductService;
import com.noterror.app.api.global.response.SingleProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class ProductController {

    private final ProductService productService;

    @PutMapping("admin/edit/{product-id}")
    public ResponseEntity patchProduct(@PathVariable("product-id") Long productId, @RequestBody Product product){
        product.setProductId(productId);
        Product Result = productService.updateProduct(productId);
        return new ResponseEntity<>(
                        new SingleProductResponse(Result),
                        HttpStatus.OK);
    }

}
