package com.noterror.app.api.entity.order;

import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "orders")
public class Orders extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long ordersId;

    @Enumerated(EnumType.STRING)
    @Column
    private OrdersStatus ordersStatus = OrdersStatus.ORDER_REQUEST;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "orders")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void addOrderProduct(OrderProduct orderProduct){
        orderProducts.add(orderProduct);
        orderProduct.setOrders(this);
    }

    public static Orders createOrder(Member member, List<OrderProduct> orderProductList) {
        Orders order = new Orders();
        order.setMember(member);
        order.setOrdersStatus(OrdersStatus.ORDER_REQUEST);
        for(OrderProduct orderProduct : orderProductList) {
            order.addOrderProduct(orderProduct);
        }
        return order;
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderProduct orderProduct : orderProducts){
            totalPrice += orderProduct.getTotalPrice();
        }
        return totalPrice;
    }
}
