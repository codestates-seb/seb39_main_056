package com.noterror.app.api.domain.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "CART_DETAIL")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartDetail_id")
    private Long cartDetailId; // CART_DETAIL_ID

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public void addCart(Cart cart) {
        this.cart = cart;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void addProduct(Product product) {
        this.product = product;
    }

    public static CartDetail createCartDetail(Cart cart, Product product, int count){
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setCount(count);

        return cartDetail;
    }

    //장바구니에 상품 수량 증가
    public void addCount(int count){
        this.count += count;
    }

    //장바구니에 담을 수량 반영
    public void updateCount(int count) {
        this.count = count;
    }
}