package com.noterror.app.api.product.service;

import com.noterror.app.api.member.repository.MemberRepository;
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

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    private String currentPageVegetarianName;

    @Override
    public String getCurrentPageVegetarianName() {
        return currentPageVegetarianName;
    }


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

    /**
     * 해당 메서드는 회원이 전체 제품 조회의 기능에 접근할 때 수행합니다.
     * 하지만 현재 비회원임에도, SecurityContextHolder 에 알 수 없는 이메일이 찍혀서 회원으로 접근하고 있습니다.
     * 당연히 db에 없는 이메일이기 때문에 null 발생으로 제품 출력에 오류가 발생하고 있습니다.
     * 현재까지 정확한 원인을 알 수 없어서 우선 해당 메서드에서 메일이 db에 있는 데이터인지 한번 더 확인하고 있습니다.
     */
    @Override
    public Page<Product> findProductsWhenAuthenticated(QueryParamDto queryParamDto, String email) {

        if (isNull(queryParamDto.getVegetarian())) {
            memberRepository.findByEmail(email).ifPresentOrElse(
                    member -> {queryParamDto.setVegetarian(member.getVegetarianType());},
                    () -> queryParamDto.setVegetarian("플렉시테리언"));
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

        currentPageVegetarianName = queryParamDto.getVegetarian();

        return productRepository.findAllByVegetarian(
                currentPageVegetarianName,
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
