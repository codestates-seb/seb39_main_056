package com.noterror.app.api.entity;

import com.noterror.app.api.entity.cart.CartDetail;
import com.noterror.app.api.entity.order.OrderProduct;
import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.global.audit.Auditable;
import com.noterror.app.api.global.exception.OutOfStockException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String thumbnailImage;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String detailImage;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "vegetarian_type_name")
    private VegetarianType vegetarianType;

    @OneToMany(mappedBy = "product")
    private List<CartDetail> cartDetail = new ArrayList<>();

    //== BUSINESS LOGIC ==//
    public void addOrdersDetail(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
        if (orderProduct.getProduct() != this) {
            orderProduct.addProduct(this);
        }
    }

    public void addVegetarianType(VegetarianType vegetarianType) {
        this.vegetarianType = vegetarianType;
    }

    public void registrationProduct(ProductRequestDto productRequestDto,
                                    VegetarianType vegetarianType) {
        this.productName = productRequestDto.getProductName();
        this.stockQuantity = productRequestDto.getStockQuantity();
        this.price = productRequestDto.getPrice();
        this.thumbnailImage = productRequestDto.getThumbnailImage();
        this.detailImage = productRequestDto.getDetailImage();
        this.vegetarianType = vegetarianType;
    }

    public void updateProductInfo(ProductRequestDto productRequestDto,
                                  VegetarianType vegetarianType) {
        this.productName = productRequestDto.getProductName();
        this.stockQuantity = productRequestDto.getStockQuantity();
        this.price = productRequestDto.getPrice();
        this.thumbnailImage = productRequestDto.getThumbnailImage();
        this.detailImage = productRequestDto.getDetailImage();
        this.vegetarianType = vegetarianType;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity; //남은 재고
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량 : " + this.stockQuantity + ")");
        }
        this.stockQuantity = restStock;
    }
}