package com.noterror.app.api.entity.order;

import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Orders extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @Enumerated(EnumType.STRING)
    @Column
    private OrdersStatus orderStatus = OrdersStatus.ORDER_REQUEST;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    //== BUSINESS LOGIC ==//
    public void addMember(Member member) {
        this.member = member;
        if (!member.getOrderList().contains(this)) {
            member.addOrders(this);
        }
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        if (orderDetail.getOrders() != this) {
            orderDetail.addOrders(this);
        }
    }

    public int getOrderTotalPrice() {
        return this.orderDetails.stream()
                .mapToInt(OrderDetail::getProductTotalPrice)
                .sum();
    }

    public void applyQuantityDecrease() {
        this.orderDetails.stream()
                .map(OrderDetail::getOrderQuantity)
                .forEach(orderQuantity -> {
                    this.orderDetails.stream()
                            .map(OrderDetail::getProduct)
                            .forEach(product ->
                                    product.decreaseStockQuantity(orderQuantity));
                });
    }
}
