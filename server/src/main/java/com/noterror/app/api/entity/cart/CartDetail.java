package com.noterror.app.api.entity.cart;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.order.OrderDetail;
import com.noterror.app.api.entity.order.Orders;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartDetailId; // CART_DETAIL_ID

    @Column
    private int purchaseQuantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //== BUSINESS LOGIC ==//
    public void addCart(Cart cart) {
        this.cart = cart;
    }

    public void addProduct(Product product) {
        this.product = product;
    }

    public void plusPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity += purchaseQuantity;
    }

    public void updatePurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public OrderDetail toOrderDetailByCartDetail() {
        return OrderDetail.builder()
                .orderQuantity(this.purchaseQuantity)
                .product(product)
                .build();
    }
}
