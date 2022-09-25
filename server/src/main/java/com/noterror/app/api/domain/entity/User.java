package com.noterror.app.api.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String provider;
    private String providerId;
    private String role;
    private String username;
    private String email;

    @Builder
    public User(String provider, String providerId, String role, String username, String email) {
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
        this.username = username;
        this.email = email;
    }
}
