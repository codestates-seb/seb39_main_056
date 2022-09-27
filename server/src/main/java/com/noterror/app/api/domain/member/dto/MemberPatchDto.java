package com.noterror.app.api.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Data
@AllArgsConstructor
public class MemberPatchDto {
    private Long memberId;

    @NotEmpty(message = "휴대전화번호를 입력해주세요")
    private String phone;

    @NotEmpty(message = "우편번호(Zipcode)를 입력해주세요")
    @Column(nullable = false)
    private String zipCode;

    @NotEmpty(message = "도시명(City)를 입력해주세요")
    @Column(nullable = false)
    private String city;

    @NotEmpty(message = "상세주소(DetailAddress)를 입력해주세요")
    @Column(nullable = false)
    private String detailAddress;

    private String vegetarianType;

    //채식유형 구현

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

}
