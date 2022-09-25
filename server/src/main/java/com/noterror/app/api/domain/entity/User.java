package com.noterror.app.api.domain.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String email;

    private String role;

    private String password;

    private int phone;

    @Embedded
    private Address address;

    private String vegetarianType;

    public User(String firstName, String lastName, String email) {
        this.username = lastName + firstName;
        this.email = email;
    }

    //TODO 가입유형

}
