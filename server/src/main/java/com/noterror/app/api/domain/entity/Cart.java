package com.noterror.app.api.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart")
    private List<CartDetail> cartProduct = new ArrayList<>();

    private int count;

    public static Cart CreateCart(Member member) {
        Cart cart = new Cart();
        cart.member = member;
        cart.count = 0;

        return cart;
    }
}
