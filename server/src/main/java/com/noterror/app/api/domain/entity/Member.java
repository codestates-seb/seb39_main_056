
package com.noterror.app.api.domain.entity;

import com.noterror.app.api.domain.member.dto.MemberRequestDto;
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
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String memberName;

    private String password;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @Column(nullable = true)
    private String vegetarianType;

    @Column(nullable = true)
    private LocalDateTime regDate = LocalDateTime.now();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    //== BUSINESS LOGIC ==//
    public void updateMemberInfo(MemberRequestDto memberRequestDto) {
        this.phone = memberRequestDto.getPhone();
        new Address(
                memberRequestDto.getZipCode(),
                memberRequestDto.getCity(),
                memberRequestDto.getDetailAddress()
        );
    }
}