package com.noterror.app.api.entity;

import com.noterror.app.api.entity.member.Member;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public void addMember(Member member) {
        this.member = member;
    }

    @OneToMany(mappedBy = "cart")
    private List<CartDetail> cartDetail = new ArrayList<>();

    public void addCartDetail(CartDetail cartDetail){
        this.cartDetail.add(cartDetail);
    }


    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.member = member;

        return cart;
    }
}
