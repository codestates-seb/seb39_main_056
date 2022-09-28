package com.noterror.app.api.domain.member.dto;

import com.noterror.app.api.domain.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberResponseDto {
    private Long memberId;
    private String memberName;
    private String email;
    private String phone;
    private String zipCode;
    private String city;
    private String detailAddress;
    private String vegetarianType;
    private LocalDateTime regDate;
}
