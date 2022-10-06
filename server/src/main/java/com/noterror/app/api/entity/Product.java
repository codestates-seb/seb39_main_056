package com.noterror.app.api.entity;

import com.noterror.app.api.entity.order.OrderProduct;
import com.noterror.app.api.domain.product.dto.ProductRequestDto;
import com.noterror.app.api.global.exception.OutOfStockException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    private LocalDateTime signDate;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String thumbnailImage;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String detailImage;
    /**
     * 제품 상세와 매핑
     */
    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "vegetarian_type_name")
    private VegetarianType vegetarianType;

    public void addOrdersDetail(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
        if (orderProduct.getProduct() != this) {
            orderProduct.addProduct(this);
        }
    }

    @OneToMany(mappedBy = "product")
    private List<CartDetail> cartProduct = new ArrayList<>();

    //== BUSINESS LOGIC ==//
    public void updateProductInfo(ProductRequestDto productPatchDto) {
        this.productName = productPatchDto.getProductName();
        this.quantity = productPatchDto.getQuantity();
        this.price = productPatchDto.getPrice();
        this.thumbnailImage = productPatchDto.getThumbnailImage();
        this.detailImage = productPatchDto.getDetailImage();
    }

    public void removeStock(int quantity) {
        int restStock = this.quantity - quantity; //남은 재고
        if(restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량 : " + this.quantity + ")");
        }
        this.quantity = restStock;
    }

    //주문 취소시 상품 개수 다시 증가
    public void addStock(int quantity) {
        this.quantity += quantity;
    }
}