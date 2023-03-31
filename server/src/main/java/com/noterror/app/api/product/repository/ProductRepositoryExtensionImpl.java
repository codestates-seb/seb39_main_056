package com.noterror.app.api.product.repository;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.QProduct;
import com.noterror.app.api.entity.QVegetarian;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProductRepositoryExtensionImpl extends QuerydslRepositorySupport implements ProductRepositoryExtension {

    public ProductRepositoryExtensionImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> findAllByVegetarian(String vegetarianType, Pageable pageable) {
        QProduct product = QProduct.product;
        QVegetarian vegetarian = QVegetarian.vegetarian;

        JPQLQuery<Product> query = from(product)
                .where(product.vegetarianType.eq(vegetarianType));

        JPQLQuery<Product> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Product> fetchResults = pageableQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
