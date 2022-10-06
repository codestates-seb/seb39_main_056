package com.noterror.app.api.domain.member.controller;

import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.domain.member.dto.VegetarianTypeInputDto;
import com.noterror.app.api.domain.member.service.MemberService;
import com.noterror.app.api.global.response.SingleMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 담당자 : 강시혁, 황윤준, 이현석
 * SCOPE : 일반 회원 관리
 * 리팩토링 : 강시혁
 * 대상 : MEMBER
 */
@Controller
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 정보 등록
     */
    @PostMapping("/sign-up")
    public ResponseEntity<MemberResponseDto> postMember(@RequestBody @Valid SignUpDto signUpDto) {
        Long newMemberId = memberService.saveMemberInfo(signUpDto);
        return new ResponseEntity(newMemberId, HttpStatus.CREATED);
    }

    /**
     * 채식 유형 등록
     */
    @PostMapping("/sign-up/type/{member-id}")
    public ResponseEntity<MemberResponseDto> postVegetarianTypeOfNewMember(
            @PathVariable("member-id") Long memberId,
            @RequestBody @Valid VegetarianTypeInputDto vegetarianType) {

        MemberResponseDto response = memberService.saveTypeOfNewMember(memberId, vegetarianType);
        return new ResponseEntity(
                new SingleMemberResponse<>(response), HttpStatus.OK);
    }

    /**
     * 회원 정보 수정
     */
    @PutMapping("/info")
    public ResponseEntity putMember(
            @RequestBody @Valid UpdateInfoDto updateInfoDto) {
        MemberResponseDto response = memberService.updateMember(currentUserEmail(), updateInfoDto);

        return new ResponseEntity<>(
                new SingleMemberResponse<>(response), HttpStatus.OK);
    }

    /**
     * 개별 회원 정보 조회
     */
    @GetMapping("/info")
    public ResponseEntity getMember() {
        MemberResponseDto response = memberService.findMember(currentUserEmail());
        return new ResponseEntity<>(
                new SingleMemberResponse(response), HttpStatus.OK);
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/info")
    public ResponseEntity deleteProduct() {
        memberService.removeMember(currentUserEmail());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String currentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}