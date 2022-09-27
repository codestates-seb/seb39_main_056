package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.product.dto.ProductPatchDto;
import com.noterror.app.api.domain.product.dto.ProductPostDto;
import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    
    private final ProductRepository productRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Product findProduct(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(()-> new NullPointerException("조회된 제품이 없습니다."));
    }

    @Override
    public Page<Product> findProductsWithPageAndSort(int page, int size, String sort, String orderBy) {

        if(orderBy.equals("desc") || orderBy.equals(null)) {
            return productRepository.findAll(
                    PageRequest.of(page, size, Sort.by(sort).descending()));
        }

        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
    }

    @Override
    // @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE) - 공부
    public Product updateProduct(ProductPatchDto product){

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
    public Product createProduct(ProductPostDto productPostDto){
        Product product = new Product();
        product.setProductName(productPostDto.getProductName());
        product.setPrice(productPostDto.getPrice());
        product.setQuantity(productPostDto.getQuantity());
        product.setDetailImage(productPostDto.getDetailImage());
        product.setThumbnailImage(productPostDto.getThumbnailImage());

        return productRepository.save(product);
    }
    
    @Override
    public void delete(Long productId) {
        Product findProduct = findExistProduct(productId);
        productRepository.delete(findProduct);
    }

    public Product findExistProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product findProduct =
                optionalProduct
                        .orElseThrow(()-> new NullPointerException("조회된 제품이 없습니다."));
        return findProduct;
    }
}
