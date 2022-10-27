package com.noterror.app.api.entity.cart;

import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.audit.Auditable;
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
public class Cart extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.PERSIST)
    private List<CartDetail> cartDetails = new ArrayList<>();

    //== BUSINESS LOGIC ==//
    public void addCartDetail(CartDetail cartDetail) {
        this.cartDetails.add(cartDetail);
        if (cartDetail.getCart() != this) {
            cartDetail.addCart(this);
        }
    }

    public void addMember(Member member) {
        this.member = member;
    }

}
