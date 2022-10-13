package com.noterror.app.api.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vegetarian {
    @Id
    @Column(unique = true)
    private String vegetarianType;

    @Column
    private Integer levels;
}
