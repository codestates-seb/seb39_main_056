package com.noterror.app.api.domain.user.dto;

import com.noterror.app.api.domain.entity.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
public class MemberRequestDto {
    @NotBlank(message = "이름을 입력해주세요.")
    private String memberName;
    @NotEmpty(message = "이메일(ID)를 입력해주세요")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;
    //@NotEmpty(message = "비밀번호를 입력해주세요")
    //private String password;
    @NotEmpty(message = "휴대전화번호를 입력해주세요")
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
