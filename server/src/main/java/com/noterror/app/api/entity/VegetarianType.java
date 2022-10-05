package com.noterror.app.api.entity;

import com.noterror.app.api.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VegetarianType {

    @Id
    @Column(unique = true)
    private String vegetarianTypeName;

    @Column
    private int vegetarianTypeLevel;

    @OneToOne
    @Transient
    private Member member;

}
