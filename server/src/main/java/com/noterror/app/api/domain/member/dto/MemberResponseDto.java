package com.noterror.app.api.domain.member.dto;

import com.noterror.app.api.entity.member.Member;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberResponseDto {
    private String memberName;
    private String email;
    private String phone;
    private String zipCode;
    private String city;
    private String detailAddress;
    private String vegetarianType;
    private LocalDateTime createDate;
    private LocalDateTime editDate;

    public MemberResponseDto(Member member) {
        this.memberName = member.getMemberName();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.zipCode = member.getAddress().getZipcode();
        this.city = member.getAddress().getCity();
        this.detailAddress = member.getAddress().getDetailAddress();
        this.vegetarianType = member.getVegetarianType().getVegetarianTypeName();
        this.createDate = member.getCreateDate();
        this.editDate = member.getEditDate();
    }
}
