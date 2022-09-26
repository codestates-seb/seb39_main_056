package com.noterror.app.api.domain.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    private String memberName;

    private String email;

    private String role;

    //private String password;

    private String phone;

    @Embedded
    private Address address;

    private String vegetarianType;

    public Member(String firstName, String lastName, String email) {
        this.memberName = lastName + firstName;
        this.email = email;
    }

    //TODO 가입유형

}
