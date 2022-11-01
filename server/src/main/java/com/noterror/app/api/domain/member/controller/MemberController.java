package com.noterror.app.api.domain.member.controller;

import com.noterror.app.api.domain.member.dto.*;
import com.noterror.app.api.domain.member.service.MemberService;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.response.SingleMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 정보 등록
     */
    @PostMapping("/sign-up")
    public ResponseEntity<MemberResponseDto> postMember(@RequestBody @Valid SignUpDto signUpDto) {
        Member member = signUpDto.toEntity();
        Long newMemberId = memberService.saveMemberInfo(member);

        return new ResponseEntity(
                new MemberIdResponseDto(newMemberId), HttpStatus.CREATED);
    }

    /**
     * 채식 유형 등록
     */
    @PostMapping("/sign-up/type/{member-id}")
    public ResponseEntity<MemberResponseDto> postVegetarianTypeOfNewMember(
            @PathVariable("member-id") Long memberId,
            @RequestBody VegetarianTypeInputDto vegetarianType) {

        Member member = memberService.saveTypeOfNewMember(memberId, vegetarianType);
        MemberResponseDto response = new MemberResponseDto(member);

        return new ResponseEntity(
                new SingleMemberResponse<>(response), HttpStatus.OK);
    }

    /**
     * 회원 정보 수정
     */
    @PutMapping("/info")
    public ResponseEntity putMember(
            @RequestBody @Valid UpdateInfoDto updateInfoDto) {
        Member currentMember = updateInfoDto.toEntityWithEmail(currentUserEmail());
        Member updateMember = memberService.updateMember(currentMember);
        MemberResponseDto response = new MemberResponseDto(updateMember);

        return new ResponseEntity<>(
                new SingleMemberResponse<>(response), HttpStatus.OK);
    }

    /**
     * 개별 회원 정보 조회
     */
    @GetMapping("/info")
    public ResponseEntity getMember() {
        Member findMember = memberService.findMemberByEmail(currentUserEmail());
        MemberResponseDto response = new MemberResponseDto(findMember);
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