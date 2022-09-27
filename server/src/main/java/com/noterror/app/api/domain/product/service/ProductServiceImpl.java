package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.mapper.ProductMapper;
import com.noterror.app.api.domain.product.repository.ProductRepository;
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
    private final ProductMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto findProduct(Long productId) {

        return mapper.productToProductResponseDto(findExistProduct(productId));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findProductsWithPageAndSort(int page, int size, String sort, String orderBy) {

        if (orderBy.equals("desc") || orderBy.equals(null)) {
            return productRepository.findAll(
                    PageRequest.of(page, size, Sort.by(sort).descending()));
        }

        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Product mappingProduct = mapper.productRequestDtoToProduct(productRequestDto);
        Product newProduct = productRepository.save(mappingProduct);
        ProductResponseDto result = mapper.productToProductResponseDto(newProduct);

        return result;
    }

    @Override
    // @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE) - 공부
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto) {

        Product findProduct = findExistProduct(productId);
        findProduct.updateProductInfo(productRequestDto);
        Product updatedProduct = productRepository.save(findProduct);

        return mapper.productToProductResponseDto(updatedProduct);
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
}
