
package com.noterror.app.api.domain.entity.member;

import com.noterror.app.api.domain.entity.VegetarianType;
import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import lombok.*;

import javax.persistence.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Member implements Principal {
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

    @OneToOne
    @JoinColumn(name = "vegetarian_type_name")
    private VegetarianType vegetarianType;

    public void setVegetarianType(VegetarianType vegetarianType) {
        this.vegetarianType = vegetarianType;
    }

    @Column(nullable = true)
    private LocalDateTime signDate = LocalDateTime.now();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    //== Constructor ==//

    public Member(String email) {
        this.email = email;
    }

    //== BUSINESS LOGIC ==//
    public void updateMemberInfo(UpdateInfoDto updateInfoDto, VegetarianType type) {
        this.phone = updateInfoDto.getPhone();
        new Address(
                updateInfoDto.getZipCode(),
                updateInfoDto.getCity(),
                updateInfoDto.getDetailAddress()
        );
        this.vegetarianType = type;
    }

    public void proceedSocialSignUp(SignUpDto signUpDto) {
        this.phone = signUpDto.getPhone();
        this.address = new Address(
                signUpDto.getZipCode(),
                signUpDto.getCity(),
                signUpDto.getDetailAddress()
        );
    }

    public void proceedGeneralSignUp(SignUpDto signUpDto, String encodedPassword) {
        this.email = signUpDto.getEmail();
        this.memberName = signUpDto.getMemberName();
        this.password = encodedPassword;
        this.phone = signUpDto.getPhone();
        this.address = new Address(
                signUpDto.getZipCode(),
                signUpDto.getCity(),
                signUpDto.getDetailAddress()
        );
    }

    // Principal 기능
    @Override
    public String getName() {
        return getEmail();
    }


}