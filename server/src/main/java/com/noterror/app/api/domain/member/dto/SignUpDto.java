package com.noterror.app.api.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    @NotEmpty(message = "이메일(ID)을 공백없이 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    /** 회원 이름 */
    @NotEmpty(message = "이름을 공백없이 입력해주세요.")
    @Length(min=3,max=5)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣]{3,5}$")
    private String memberName;

    @Nullable
    @Length(min = 8, max = 16, message = "비밀번호는 8글자 이상 16 글자 이하로 작성해야 합니다.")
    private String password;

    @NotEmpty(message = "전화번호를 공백없이 입력해주세요.")
    @Pattern(regexp = "^010\\d{3,4}\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자를 구분자(-) 없이 입력해주세요.")
    private String phone;

    /** 우편번호 */
    @NotEmpty
    private String zipCode;

    /** 도로명 주소 */
    @NotEmpty
    private String city;

    /** 상세 주소(사용자 입력) */
    private String detailAddress;
}
