package com.noterror.app.api.domain.admin;

import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.service.ProductService;
import com.noterror.app.api.domain.vegetarian.dto.VegetarianTypeResponseDto;
import com.noterror.app.api.domain.vegetarian.service.VegetarianTypeService;
import com.noterror.app.api.global.response.SingleProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 담당자 : 이현석, 홍민정
 * SCOPE : ADMIN PAGE, ROLE_ADMIN
 * 리팩토링 : 강시혁
 * 대상 : Product
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final VegetarianTypeService vegetarianTypeService;

    /**
     * 제품 등록
     * @RequestBody 제품명, 수량, 금액, 썸네일 이미지, 상세 이미지
     */
    @PostMapping("/registration")
    public ResponseEntity<ProductResponseDto> postProduct(@RequestBody ProductRequestDto productRequestDto) {

        ProductResponseDto response = productService.createProduct(productRequestDto);
        List<VegetarianTypeResponseDto> findVegeTypes = vegetarianTypeService.getVegetarianType("프루테리언");

        return new ResponseEntity(
                new SingleProductResponse(response), HttpStatus.CREATED);
    }

    /**
     * 제품 수정
     * @PathVariable 제품 식별자
     * @RequestBody 제품명, 수량, 금액, 썸네일 이미지, 상세 이미지
     */
    @PutMapping("/edit/{product-id}")
    public ResponseEntity putProduct(@PathVariable("product-id") Long productId, @Valid @RequestBody ProductRequestDto productRequestDto) {

        ProductResponseDto response = productService.updateProduct(productId, productRequestDto);

        return new ResponseEntity<>(
                new SingleProductResponse<>(response),
                HttpStatus.OK);
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
