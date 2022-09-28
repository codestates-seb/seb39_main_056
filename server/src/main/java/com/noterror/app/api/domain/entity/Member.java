package com.noterror.app.api.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long memberId;

    @Column(nullable = false)
    private String memberName;

//    @Column(nullable = false)
//    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    /** 우편번호 */
    @Column(nullable = false)
    private int zipCode;

    /** 도로명 주소 */
    @Column(nullable = false)
    private String city;

    /** 상세 주소(사용자 입력) */
    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = true)
    private String vegetarianType;

    @Column(nullable = true)
    private LocalDateTime regDate = LocalDateTime.now();

//    /** 소셜명 */
//    @Column(nullable = false)
//    private String signupType;

    /** Admin/User 역할 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;
}