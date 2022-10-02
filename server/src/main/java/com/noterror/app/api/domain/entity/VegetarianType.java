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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String vegetarianTypeName;

    @Column
    private int vegetarianTypeLevel;

    @OneToOne
    private Member member;

    //== BUSINESS LOGIC ==//
}
