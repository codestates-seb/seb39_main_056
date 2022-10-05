package com.noterror.app.api.entity;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long categoryId;

    @Column
    private String categoryName;

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
