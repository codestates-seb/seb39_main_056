package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.entity.Product;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setProductName(productRequestDto.getProductName());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setDetailImage(productRequestDto.getDetailImage());
        product.setThumbnailImage(productRequestDto.getThumbnailImage());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductResponseDto productResponseDto) {
        return null;
    }

    @Override
    public void delete(long productId) {
        Product findProduct = findVerifiedProduct(productId);
        productRepository.delete(findProduct);
    }

    public Product findVerifiedProduct(long productId) {
        Optional<Product> optionalProduct =
                productRepository.findById(productId);
        Product findProduct =
                optionalProduct.orElseThrow(() ->
                        new NullPointerException("삭제하려는 제품이 없습니다."));
        return findProduct;
    }

}
