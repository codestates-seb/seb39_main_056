package com.noterror.app.api.entity.order;

import com.noterror.app.api.entity.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_product_id")
    private Long ordersProductId;

    @Column(nullable = false)
    private int ordersQuantity;

    private int ordersPrice;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public static OrderProduct createOrderProduct(Product product, int ordersQuantity){

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrdersProductId(orderProduct.getOrdersProductId());
        orderProduct.setOrdersQuantity(ordersQuantity);
        orderProduct.setOrdersPrice(product.getPrice());

        product.removeStock(ordersQuantity);
        return orderProduct;
    }
    public int getTotalPrice(){
        return ordersPrice* ordersQuantity;
    }

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
