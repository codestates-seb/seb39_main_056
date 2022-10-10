
package com.noterror.app.api.entity.member;

import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.entity.cart.Cart;
import com.noterror.app.api.entity.VegetarianType;
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

    private String password;

    @Column(nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "member")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "vegetarian_type_name")
    private VegetarianType vegetarianType;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    //== BUSINESS LOGIC ==//
    public void addCart(Cart cart) {
        this.cart = cart;
    }

    public void setVegetarianType(VegetarianType vegetarianType) {
        this.vegetarianType = vegetarianType;
    }

    public void updateMemberInfo(UpdateInfoDto updateInfoDto, VegetarianType type) {
        this.phone = updateInfoDto.getPhone();
        this.address = new Address(
                updateInfoDto.getZipCode(),
                updateInfoDto.getCity(),
                updateInfoDto.getDetailAddress()
        );
        this.vegetarianType = type;
    }

    public void proceedGeneralSignUp(SignUpDto signUpDto, List<String> roles, String password) {
        this.email = signUpDto.getEmail();
        this.memberName = signUpDto.getMemberName();
        this.phone = signUpDto.getPhone();
        this.password = password;
        this.address = new Address(
                signUpDto.getZipCode(),
                signUpDto.getCity(),
                signUpDto.getDetailAddress());
        this.roles = roles;
    }

    // Principal 기능
    @Override
    public String getName() {
        return getEmail();
    }
}