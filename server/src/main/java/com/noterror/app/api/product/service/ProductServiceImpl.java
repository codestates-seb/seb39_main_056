package com.noterror.app.api.product.service;

import com.noterror.app.api.product.dto.QueryParamDto;
import com.noterror.app.api.product.repository.ProductRepository;
import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findProduct(Long productId) {
        return findExistProduct(productId);
    }

    @Override
    public Page<Product> findProductsWhenAnonymous(QueryParamDto queryParamDto) {
        if (isNull(queryParamDto.getVegetarian())) {
            queryParamDto.setVegetarian("플렉시테리언");
        }
        return getAllByQueryParam(queryParamDto);
    }

    @Override
    public Page<Product> findProductsWhenAuthenticated(QueryParamDto queryParamDto, Member member) {
        if (isNull(queryParamDto.getVegetarian())) {
            queryParamDto.setVegetarian(member.getVegetarianType());
        }
        return getAllByQueryParam(queryParamDto);
    }

    @Override
    @Transactional
    public Product createProduct(Product request) {
        return productRepository.save(request);
    }

    @Override
    @Transactional
    public Product updateProduct(Long productId, Product request) {
        Product findProduct = findExistProduct(productId);
        findProduct.updateInfo(request);
        return findProduct;
    }

    @Override
    @Transactional
    public void removeProduct(Long productId) {
        Product findProduct = findExistProduct(productId);
        productRepository.delete(findProduct);
    }

    public Product findExistProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.PRODUCT_NOT_FOUND));
    }

    private boolean isNull(String vegetarianType) {
        return vegetarianType == null;
    }

    private Page<Product> getAllByQueryParam(QueryParamDto queryParamDto) {
        Sort sort = getSort(
                queryParamDto.getSort(),
                queryParamDto.getOrderBy()
        );
        return productRepository.findAllByVegetarian(
                queryParamDto.getVegetarian(),
                PageRequest.of(
                        queryParamDto.getPage(),
                        queryParamDto.getSize(),
                        sort));
    }

    private Sort getSort(String sort, String orderBy) {
        if (isDescending(orderBy)) {
            return Sort.by(sort).descending();
        }
        return Sort.by(sort);
    }

    private boolean isDescending(String orderBy) {
        return orderBy.equals("desc");
    }
}
