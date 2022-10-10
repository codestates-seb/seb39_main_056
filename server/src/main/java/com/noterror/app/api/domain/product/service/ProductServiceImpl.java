package com.noterror.app.api.domain.product.service;

import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.domain.product.dto.ProductResponseDto;
import com.noterror.app.api.domain.product.dto.QueryParamDto;
import com.noterror.app.api.domain.product.repository.ProductRepository;
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

import static com.noterror.app.api.global.exception.ExceptionCode.MEMBER_NOT_FOUND;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    public ProductResponseDto findProduct(Long productId) {
        Product findProduct = findExistProduct(productId);
        return new ProductResponseDto(findProduct);
    }

    @Override
    public Page<Product> findProductsWhenAnonymous(QueryParamDto queryParamDto) {
        String orderBy = queryParamDto.getOrderBy();
        int page = queryParamDto.getPage() - 1;
        String sort = queryParamDto.getSort();
        int size = queryParamDto.getSize();
        String vegetarianType = queryParamDto.getVegetarian();

        if (isAscending(orderBy)) {
            return productRepository.findAllByVegetarianTypeName(vegetarianType, PageRequest.of(page, size, Sort.by(sort)));
        }
        return productRepository.findAllByVegetarianTypeName(vegetarianType, PageRequest.of(page, size, Sort.by(sort).descending()));
    }

    @Override
    public Page<Product> findProductsWhenAuthenticated(QueryParamDto queryParamDto, String email) {

        String orderBy = queryParamDto.getOrderBy();
        int page = queryParamDto.getPage() - 1;
        String sort = queryParamDto.getSort();
        int size = queryParamDto.getSize();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
        String vegetarianTypeName = member.getVegetarianType();

        if (isAscending(orderBy)) {
            return productRepository.findAllByVegetarianTypeName(vegetarianTypeName, PageRequest.of(page, size, Sort.by(sort)));
        }
        return productRepository.findAllByVegetarianTypeName(vegetarianTypeName, PageRequest.of(page, size, Sort.by(sort).descending()));
    }

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product newProduct = new Product();
        newProduct.registrationProduct(productRequestDto);
        newProduct = productRepository.save(newProduct);

        return new ProductResponseDto(newProduct);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto) {
        Product findProduct = findExistProduct(productId);
        findProduct.updateProductInfo(productRequestDto);

        return new ProductResponseDto(findProduct);
    }

    @Override
    @Transactional
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
