package com.noterror.app.api.domain.member.controller;

import com.noterror.app.api.domain.member.dto.MemberRequestDto;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.memberService.MemberService;
import com.noterror.app.api.global.response.SingleMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * 담당자 : 황윤준, 이현석
 * SCOPE : 일반 회원 관리
 * 리팩토링 : 이현석, 강시혁
 * 대상 : MEMBER
 */
@RequestMapping(value = "/")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원 정보 등록
     */
    @PostMapping(value = "oauth/sign-up")
    @Transactional
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto response = memberService.saveMemberInfo(memberRequestDto);
        return new ResponseEntity(new SingleMemberResponse<>(response), HttpStatus.CREATED);
    }

    /**
     * 회원 정보 수정
     */
    @PutMapping("/myPage/info/{member-id}")
    public ResponseEntity putMember(@PathVariable("member-id") Long memberId, @RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto response = memberService.updateMember(memberId, memberRequestDto);
        return new ResponseEntity<>(
                new SingleMemberResponse<>(response)
                , HttpStatus.OK);
    }

    /**
     * 개별 회원 정보 조회
     */
    @GetMapping("/myPage/info/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") Long memberId) {
        MemberResponseDto response = memberService.findMember(memberId);
        return new ResponseEntity<>(
                new SingleMemberResponse(response), HttpStatus.OK);
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/myPage/info/{member-id}")
    public ResponseEntity deleteProduct(@PathVariable("member-id") long memberId) {
        memberService.removeMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
