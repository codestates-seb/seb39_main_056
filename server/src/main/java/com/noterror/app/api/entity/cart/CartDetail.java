package com.noterror.app.api.entity.cart;

import com.noterror.app.api.entity.Product;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "cart_detail")
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
    public void getCart(Cart cart) {
        this.cart = cart;
    }

    public void getProduct(Product product) {
        this.product = product;
    }

    public void addPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity += purchaseQuantity;
    }

    public void updatePurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public static CartDetail createCartDetail(Cart cart, Product product, int count) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setPurchaseQuantity(count);
        return cartDetail;
    }
}
