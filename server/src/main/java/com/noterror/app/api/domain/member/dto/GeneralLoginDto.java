package com.noterror.app.api.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 일반 로그인(인증)시 입력받는 정보
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralLoginDto {

    @NotEmpty(message = "이메일(ID)을 공백없이 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotNull
    @Length(min = 8, max = 16, message = "비밀번호는 8글자 이상 16 글자 이하로 작성해야 합니다.")
    private String password;
}
