package com.noterror.app.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private int phone;

    @Column(nullable = false)
    private int zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    private String vegetarianType;

    @Column(nullable = false)
    private String signupType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public String getRole() {
        return this.role.getValue();
    }
}
