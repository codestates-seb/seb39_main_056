package com.noterror.app.api.entity;

import com.noterror.app.api.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Integer level;
}
