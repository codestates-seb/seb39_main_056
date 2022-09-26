package com.noterror.app.api.domain.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
public class MemberRegistrationRequestDto {

    /** 회원 이름 */
    @NotBlank(message = "이름을 입력해주세요.")
    private String memberName;

    /** 이메일 */
    @NotEmpty(message = "이메일(ID)를 입력해주세요")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    /** 휴대전화번호 */
    @NotEmpty(message = "휴대전화번호를 입력해주세요")
    private String phone;

    /** 우편번호 */
    @NotEmpty
    private String zipCode;

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
