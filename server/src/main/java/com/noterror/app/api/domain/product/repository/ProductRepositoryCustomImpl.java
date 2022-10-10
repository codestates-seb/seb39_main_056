package com.noterror.app.api.domain.product.repository;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.QProduct;
import com.noterror.app.api.entity.QVegetarianType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public ProductRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Product> findAllByVegetarianType(PageRequest of, String vegetarianTypeName) {
        return null;
                /*
               jpaQueryFactory.selectFrom(QProduct.product)
                .where(QProduct.product.vegetarianType
                        .in(jpaQueryFactory
                                .selectFrom(QVegetarianType.vegetarianType)
                                .where(QVegetarianType.vegetarianType.vegetarianTypeLevel
                                .in(jpaQueryFactory
                                        .select(QVegetarianType.vegetarianType.vegetarianTypeLevel)
                                        .from(QVegetarianType.vegetarianType)
                                        .where(QVegetarianType.vegetarianType.vegetarianTypeName
                                                .eq(vegetarianTypeName))))))
                        .fetch();

                 */

    }
}
