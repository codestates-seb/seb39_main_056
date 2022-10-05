package com.noterror.app.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)//접근제한자 Protected, Entity Proxy 조회를 위해 Protected 사용
public class Category {
    /**
    * 카테고리 ID
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long categoryId;

    /**
     * 카테고리 이름
     */
    @Column
    private String categoryName;

    /**
     * 부모 카테고리(참조 FK)
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn
    @Nullable
    //@JsonManagedReference
    private Category parent;

    /**
     * 카테고리 깊이
     */
    @Column
    private Long depth;

    /**
     * 자식 카테고리, 하위 카테고리를 List 형태로 호출
     */
    @OneToMany(mappedBy = "parent")
    //@JsonBackReference
    private List<Category> children = new ArrayList<>();


}
