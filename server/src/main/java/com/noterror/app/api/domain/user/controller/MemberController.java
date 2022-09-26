package com.noterror.app.api.domain.user.controller;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.dto.MemberRegistrationRequestDto;
import com.noterror.app.api.domain.user.dto.MemberRegistrationResponseDto;
import com.noterror.app.api.domain.user.userService.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/")
@Controller
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /** 회원 가입 */
    @PostMapping(value = "oauth/sign-up")
    @Transactional
    public ResponseEntity<MemberRegistrationResponseDto> createUser(@RequestBody MemberRegistrationRequestDto memberRegistrationRequestDto){

        Member memberData = memberService.create(memberRegistrationRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /** 회원 탈퇴 */
    @DeleteMapping("/myPage/info/{memberId}")
    public ResponseEntity deleteProduct(@PathVariable("memberId") long memberId) {
        memberService.delete(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
