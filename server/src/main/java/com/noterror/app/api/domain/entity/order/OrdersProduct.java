package com.noterror.app.api.domain.entity.order;

import com.noterror.app.api.domain.entity.Product;
import com.noterror.app.api.domain.entity.order.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrdersProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersProductId;

    @Column(nullable = false)
    private int quantity;

    private int ordersPrice;
    @ManyToOne
    @JoinColumn(name = "ordersId") //테이블에 ordersId 라는 컬럼명이 정의되는 것
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    public static OrdersProduct createOrderProduct(Product product, int quantity){

        OrdersProduct ordersProduct = new OrdersProduct();
        ordersProduct.setProduct(product);
        ordersProduct.setQuantity(quantity);
        ordersProduct.setOrdersPrice(product.getPrice());

        return ordersProduct;
    }
    public int getTotalPrice(){return ordersPrice*quantity;}



    public void addOrders(Orders orders) {
        this.orders = orders;
        if (!this.orders.getOrdersProducts().contains(this)) {
            this.orders.getOrdersProducts().add(this);
        }
    }
    public void addProduct(Product product) {
        this.product = product;
        if (!this.product.getOrdersProducts().contains(this)) {
            this.product.addOrdersDetail(this);
        }
    }


}
