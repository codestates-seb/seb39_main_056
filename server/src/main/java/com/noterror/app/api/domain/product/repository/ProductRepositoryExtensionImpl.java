package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.QProduct;
import com.noterror.app.api.entity.QVegetarianType;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryExtensionImpl extends QuerydslRepositorySupport implements ProductRepositoryExtension {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public ProductRepositoryExtensionImpl(Class<?> domainClass) {
        super(Product.class);
    }

    @Override
    public Page<Product> findByVegetarianType(List<String> vegetarian, Pageable pageable) {

        QProduct product = QProduct.product;

        JPQLQuery<Product> query =
                from(product)
                        .where(product.vegetarianType);

        JPQLQuery<Product> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Product> fetchResults = pageableQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
