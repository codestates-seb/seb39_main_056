package com.noterror.app.api.domain.entity.order;

import com.noterror.app.api.domain.entity.Member;
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
@Table(name = "ORDERS")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long ordersId;

    private LocalDateTime ordersDate;

    /**
     * 주문 상태
     */
    @Enumerated(EnumType.STRING)
    private OrdersStatus ordersStatus = OrdersStatus.ORDER_REQUEST;

    /**
     * 회원과 다대일 매핑
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    /**
     * 주문상세와 일대다 매핑
     */
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
        order.setOrdersDate(LocalDateTime.now());
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
