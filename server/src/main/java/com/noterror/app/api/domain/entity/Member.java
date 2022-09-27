package com.noterror.app.api.domain.entity;

import com.noterror.app.api.domain.member.dto.MemberPatchDto;
import com.noterror.app.api.domain.member.dto.MemberRequestDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long memberId;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    /** 우편번호 */
    @Column(nullable = false)
    private String zipCode;

    /** 도로명 주소 */
    @Column(nullable = false)
    private String city;

    /** 상세 주소(사용자 입력) */
    @Column(nullable = false)
    private String detailAddress;

    private String vegetarianType;

    @Column(nullable = true)
    private LocalDateTime regDate = LocalDateTime.now();

    public void updateProductInfo(MemberPatchDto memberPatchDto){
        this.memberId = memberPatchDto.getMemberId();
        this.phone = memberPatchDto.getPhone();
        this.zipCode = memberPatchDto.getZipCode();
        this.city = memberPatchDto.getCity();
        this.detailAddress = memberPatchDto.getDetailAddress();
        this.vegetarianType = memberPatchDto.getVegetarianType();
    }

//    /** 소셜명 */
//    @Column(nullable = false)
//    private String signupType;

}