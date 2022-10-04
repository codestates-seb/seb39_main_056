package com.noterror.app.api.domain.entity.order;

import com.noterror.app.api.domain.entity.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_product_id")
    private Long ordersProductId;

    @Column(nullable = false)
    private int quantity;

    private int ordersPrice;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orders_id") //테이블에 ordersId 라는 컬럼명이 정의되는 것
    private Orders orders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public static OrderProduct createOrderProduct(Product product, int quantity){

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrdersProductId(product.getProductId());
        orderProduct.setQuantity(quantity);
        orderProduct.setOrdersPrice(product.getPrice());
        product.removeStock(quantity);
        return orderProduct;
    }
    public int getTotalPrice(){return ordersPrice*quantity;}

    public void addOrders(Orders orders) {
        this.orders = orders;
        if (!this.orders.getOrderProducts().contains(this)) {
            this.orders.getOrderProducts().add(this);
        }
    }
    public void addProduct(Product product) {
        this.product = product;
        if (!this.product.getOrderProducts().contains(this)) {
            this.product.addOrdersDetail(this);
        }
    }


}
