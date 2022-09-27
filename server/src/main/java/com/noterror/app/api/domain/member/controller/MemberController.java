package com.noterror.app.api.domain.member.controller;

import com.noterror.app.api.domain.member.dto.MemberPatchDto;
import com.noterror.app.api.domain.member.dto.MemberRequestDto;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.global.response.SingleMemberResponse;
import com.noterror.app.api.domain.member.userService.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
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
    public ResponseEntity<MemberResponseDto> createUser(@RequestBody MemberRequestDto memberRequestDto){

        MemberResponseDto memberData = memberService.createMember(memberRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /** 회원 탈퇴 */
    @DeleteMapping("/myPage/info/{memberId}")
    public ResponseEntity deleteProduct(@PathVariable("memberId") long memberId) {
        memberService.delete(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * 회원 정보 수정
     */
    @PutMapping("/myPage/info/{memberId}")
    public ResponseEntity updateMember(@PathVariable("memberId") Long memberId, @RequestBody MemberPatchDto memberPatchDto) {

        MemberResponseDto member =
                memberService.updateMember(memberId, memberPatchDto);
        return new ResponseEntity<>(
                new SingleMemberResponse<>(member)
                        ,HttpStatus.OK);
    }
    /**
     * 개별 회원 정보 조회
     */
    @GetMapping("/myPage/info/{memberId}")
    public ResponseEntity getMember(@PathVariable("memberId")Long memberId){
        MemberResponseDto memberResult = memberService.findMember(memberId);

        return new ResponseEntity<>(
                new SingleMemberResponse(memberResult),HttpStatus.OK);
    }
}
