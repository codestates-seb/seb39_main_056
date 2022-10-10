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
@Table(name = "vegetarian_type")
public class VegetarianType {
    @Id
    @Column(unique = true, name = "vegetarian_type_name")
    private String vegetarianTypeName;

    @Column
    private Integer vegetarianTypeLevel;

    @OneToOne
    @Transient
    private Member member;

    @OneToMany(mappedBy = "vegetarianType", targetEntity = Product.class)
    @Transient
    private List<Product> products = new ArrayList<>();
}
