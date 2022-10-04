package com.noterror.app.api.domain.entity;

import com.noterror.app.api.domain.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VegetarianType {

    @Id
    @Column(unique = true)
    private String vegetarianTypeName;

    @Column
    private int vegetarianTypeLevel;

    @OneToOne
    @Transient
    private Member member;

    //== BUSINESS LOGIC ==//
}
