package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto findProduct(Long productId) {
        Product findProduct = findExistProduct(productId);
        return new ProductResponseDto(findProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findProductsWithPageAndSort(int page, int size, String sort, String orderBy) {

        if (isAscending(orderBy)) {
            return productRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
        }
        return productRepository.findAll(
                PageRequest.of(page, size, Sort.by(sort).descending()));
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.registProduct(productRequestDto);
        Product newProduct = productRepository.save(product);
        return new ProductResponseDto(newProduct);
    }

    @Override
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto) {
        Product findProduct = findExistProduct(productId);
        findProduct.updateProductInfo(productRequestDto);
        Product updatedProduct = productRepository.save(findProduct);
        return new ProductResponseDto(updatedProduct);
    }

    @Override
    public void removeProduct(long productId) {
        Product findProduct = findExistProduct(productId);
        productRepository.delete(findProduct);
    }

    public Product findExistProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.PRODUCT_NOT_FOUND));
    }

    private boolean isAscending(String orderBy) {
        return orderBy.equals("asc");
    }
}
