package com.noterror.app.api.domain.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Getter
@Setter
public class MemberRequestDto {
    @NotBlank(message = "이름을 입력해주세요.")
    private String memberName;

    @NotEmpty(message = "이메일(ID)를 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "^010\\d{3,4}\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자를 구분자(-) 없이 입력해주세요.")
    private String phone;

    /** 우편번호 */
    @NotEmpty
    private int zipCode;

    /** 도로명 주소 */
    @NotEmpty
    private String city;

    /** 상세 주소(사용자 입력) */
    @NotEmpty
    private String detailAddress;

 //   private String role = "ROLE_USER";
 //   private String vegetarianType;
//    private String signupType;
}
