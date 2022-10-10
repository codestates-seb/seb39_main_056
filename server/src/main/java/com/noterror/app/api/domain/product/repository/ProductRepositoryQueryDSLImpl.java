package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.QProduct;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;

public class ProductRepositoryQueryDSLImpl extends QuerydslRepositorySupport implements ProductRepositoryQueryDSL {

    public ProductRepositoryQueryDSLImpl() {
        super(Product.class);
    }

    @Override
    public List<Long> findProductIdByVegetarianType(String type) {
        QProduct product = QProduct.product;

        JPQLQuery<Long> query =
                select(product.productId)
                        .from(product)
                        .where();

        return query.fetch();
    }

    @Override
    public Page<Product> findProductById(List<Long> productIds, Pageable pageable) {

        QProduct product = QProduct.product;

        JPQLQuery<Product> query =
                from(product)
                        .where();
        JPQLQuery<Product> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Product> fetchResults = pageableQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
