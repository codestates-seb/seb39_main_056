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
    /** 회원 식별자 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long memberId;

    /** 회원 이름 */
    @Column(nullable = false)
    private String memberName;

    /** 이메일 */
    @Column(unique = true, nullable = false)
    private String email;

    /** 휴대전화번호 */
    @Column(nullable = false)
    private String phone;

    /** 우편번호 */
    @Column(nullable = false)
    private String zipCode;

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


    /** Admin/User 역할구분 */
    @Column(nullable = true)
    private Role role;
}