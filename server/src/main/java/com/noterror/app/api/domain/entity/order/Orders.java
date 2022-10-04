package com.noterror.app.api.domain.entity.order;

import com.noterror.app.api.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    private LocalDateTime ordersDate;

    /**
     * 회원과 다대일 매핑
     */
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    /**
     * 주문상세와 일대다 매핑
     */
    @OneToMany(mappedBy = "orders",cascade = CascadeType.PERSIST)
    private List<OrdersProduct> ordersProducts = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void addOrdersMember(OrdersProduct ordersProduct) {
        this.ordersProducts.add(ordersProduct);
        if (ordersProduct.getOrders() != this) {
            ordersProduct.addOrders(this);
        }
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrdersProduct ordersProduct: ordersProducts){
            totalPrice += ordersProduct.getTotalPrice();
        }
        return totalPrice;
    }

    /**
     * 주문 상태
     */
    @Enumerated(EnumType.STRING)
    private OrdersStatus ordersStatus = OrdersStatus.ORDER_REQUEST;

}
