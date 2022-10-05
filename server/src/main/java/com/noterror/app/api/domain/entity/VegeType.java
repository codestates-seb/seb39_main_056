package com.noterror.app.api.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/*
@SqlResultSetMapping(
        name = "vegetypeList",
        classes = @ConstructorResult(
                targetClass = VegeTypeResponseDto.class,
                columns = {
                        @ColumnResult(name="vegeTypeName", type = String.class)
                })
)

 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)//접근제한자 Protected, Entity Proxy 조회를 위해 Protected 사용
public class VegeType {

    /**
     * 채식유형 이름
     */
    @Id
    @Column(unique = true)
    private String vegeTypeName;

    /**
     * 채식유형 레벨
     */
    @Column
    private Long vegeTypeLevel;

    // ----- 일단 무시
    /**
     * 포함하고 있는 채식유형
     */
   // @Column
   // private List<VegeType> vegeTypeList = new ArrayList<>();

}
