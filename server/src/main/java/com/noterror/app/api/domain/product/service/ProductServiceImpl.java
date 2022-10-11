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
        if (isNullInputType(queryParamDto.getVegetarian())) {
            queryParamDto.setVegetarian("플렉시테리언");
        }
        return getAllByVegetarianType(queryParamDto);
    }

    @Override
    public Page<Product> findProductsWhenAuthenticated(QueryParamDto queryParamDto, String email) {
        if (isNullInputType(queryParamDto.getVegetarian())) {
            Member member = getMember(email);
            queryParamDto.setVegetarian(member.getVegetarianType());
        }
        return getAllByVegetarianType(queryParamDto);
    }

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.registrationProduct(productRequestDto);
        Product newProduct = productRepository.save(product);

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

    private boolean isDescending(String orderBy) {
        return orderBy.equals("desc");
    }

    private boolean isNullInputType(String vegetarianType) {
        return vegetarianType == null;
    }

    private Member getMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
    }

    private Page<Product> getAllByVegetarianType(QueryParamDto queryParamDto) {

        Sort sort = Sort.by(queryParamDto.getSort());

        if (isDescending(queryParamDto.getOrderBy())) {
            sort = Sort.by(queryParamDto.getSort()).descending();
        }
        return productRepository.findAllByVegetarianTypeName(
                queryParamDto.getVegetarian(),
                PageRequest.of(queryParamDto.getPage(), queryParamDto.getSize(), sort)
        );
    }
}
