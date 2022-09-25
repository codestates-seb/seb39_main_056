package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Product updateProduct(Product product){

        Product findProduct = findExistProduct(product.getProductId());

        Optional.ofNullable(product.getProductName())
                .ifPresent(productName -> findProduct.setProductName(productName));
        Optional.ofNullable(product.getPrice())
                .ifPresent(price -> findProduct.setPrice(price));
        Optional.ofNullable(product.getQuantity())
                .ifPresent(quantity -> findProduct.setQuantity(quantity));
        Optional.ofNullable(product.getDetailImage())
                .ifPresent(detailImage -> findProduct.setDetailImage(detailImage));
        Optional.ofNullable(product.getThumbnailImage())
                .ifPresent(thumbnailImage -> findProduct.setThumbnailImage(thumbnailImage));

        return productRepository.save(findProduct);
    }

    @Override
    public Product findExistProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product findProduct =
                optionalProduct
                        .orElseThrow(()-> new NullPointerException("조회된 제품이 없습니다."));
        return findProduct;
    }
}
