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
    @Column(name = "cart_id")
    private Long cartId;

    private int count;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public void addMember(Member member) {
        this.member = member;
    }

    @OneToMany(mappedBy = "cart")
    private List<CartDetail> cartProduct = new ArrayList<>();

    public void addCartDetail(CartDetail cartDetail){
        this.cartProduct.add(cartDetail);
    }


    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.member = member;
        cart.count = 0;

        return cart;
    }
}
