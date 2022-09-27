package com.noterror.app.api.domain.user.controller;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.dto.MemberRequestDto;
import com.noterror.app.api.domain.user.dto.MemberResponseDto;
import com.noterror.app.api.domain.user.userService.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@RequestMapping
@Controller
@AllArgsConstructor
@Validated
public class MemberController {
    private final MemberService memberService;
    //private final PasswordEncoder passwordEncoder;

    /** 회원 가입 */
    @PostMapping(value = "/oauth/sign-up")
    public ResponseEntity<MemberResponseDto> createUser(@RequestBody @Valid MemberRequestDto memberRequestDto){

        Member memberData = memberService.create(memberRequestDto);
        return new ResponseEntity(new MemberResponseDto(), HttpStatus.CREATED);
    }

}
