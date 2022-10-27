
package com.noterror.app.api.entity.member;

import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.global.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Member extends Auditable implements Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String memberName;

    @Column
    private String password;

    @Column(nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @Column
    private String vegetarianType;

    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Cart cart;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "member_id")
    )
    private List<String> roles = new ArrayList<>();

    //== BUSINESS LOGIC ==//
    public void addCart(Cart cart) {
        this.cart = cart;
        if (cart.getMember() != this) {
            cart.addMember(this);
        }
    }

    public void updateMemberInfo(Member member) {
        this.phone = member.getPhone();
        this.address = member.getAddress();
        this.vegetarianType = member.getVegetarianType();
    }

    public void insertAuthInfo(List<String> roles, String password) {
        this.password = password;
        this.roles = roles;
    }

    // Principal 기능
    @Override
    public String getName() {
        return getEmail();
    }
}