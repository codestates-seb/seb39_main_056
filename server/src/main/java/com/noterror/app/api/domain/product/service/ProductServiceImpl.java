package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
