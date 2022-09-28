package com.noterror.app.api.domain.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * 일반 로그인(인증) DTO
 */
@Data
@Getter
@Setter
public class GeneralLoginForm {

    @NotEmpty(message = "이메일(ID)를 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotNull
    @Length(min=8, max=50)
    private String password;
}
