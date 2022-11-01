package com.noterror.app.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.noterror.app.api.entity.cart.CartDetail;
import com.noterror.app.api.entity.order.OrderDetail;
import com.noterror.app.api.global.audit.Auditable;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
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

    private String vegetarianType;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<CartDetail> cartDetail = new ArrayList<>();

    //== BUSINESS LOGIC ==//
    public void addOrdersDetail(OrderDetail orderDetail) {
        this.orderDetails.add(orderDetail);
        if (orderDetail.getProduct() != this) {
            orderDetail.addProduct(this);
        }
    }

    public void updateInfo(Product request) {
        this.productName = request.getProductName();
        this.stockQuantity = request.getStockQuantity();
        this.price = request.getPrice();
        this.thumbnailImage = request.getThumbnailImage();
        this.detailImage = request.getDetailImage();
        this.vegetarianType = request.getVegetarianType();
    }

    public void decreaseStockQuantity(int oquantity) {
        int restStock = this.stockQuantity - oquantity; //남은 재고
        if (restStock < 0) {
            throw new BusinessLogicException(ExceptionCode.PRODUCT_SOLD_OUT);
        }
        this.stockQuantity = restStock;
    }
}