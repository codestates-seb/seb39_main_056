package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Product findProduct(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(()-> new NullPointerException("조회된 제품이 없습니다."));
    }

    @Override
    public Page<Product> findProductsWithPageAndSort(int page, int size, String sort, String orderBy) {
        return null;
    }

    @Override
    public List<Product> pageToListProducts(Page page) {
        return null;
    }
}
