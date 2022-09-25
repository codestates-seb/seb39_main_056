package com.noterror.app.api.domain.product.controller;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.dto.ProductPatchDto;
import com.noterror.app.api.domain.product.mapper.ProductMapper;
import com.noterror.app.api.domain.product.service.ProductService;
import com.noterror.app.api.global.response.SingleProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper mapper;

    @PutMapping("/admin/edit/{product-id}")
    public ResponseEntity patchProduct(@PathVariable("product-id") Long productId, @RequestBody ProductPatchDto productPatchDto){
        productPatchDto.setProductId(productId);
        Product Result = productService.updateProduct(mapper.productPatchDtoToProduct(productPatchDto));

        return new ResponseEntity<>(
                        new SingleProductResponse<>(mapper.productToProductResponseDto(Result)),
                        HttpStatus.OK);
    }
}
