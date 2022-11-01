package com.noterror.app.api.entity.order;

import com.noterror.app.api.entity.Product;
import com.noterror.app.api.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    @Column(nullable = false)
    private int orderQuantity;

    private int productTotalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //== BUSINESS LOGIC ==//
    public void addOrders(Orders orders) {
        this.orders = orders;
        if (!orders.getOrderDetails().contains(this)) {
            orders.addOrderDetail(this);
        }
    }
    public void addProduct(Product product) {
        this.product = product;
        if (!product.getOrderDetails().contains(this)) {
            product.addOrdersDetail(this);
        }
    }

    public Orders toOrdersWithMember(Member member) {
        this.orders.addMember(member);
        addOrders(this.orders);
        return this.orders;
    }
}
