package com.noterror.app.api.domain.member.service;

import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.domain.member.dto.VegetarianTypeInputDto;
import com.noterror.app.api.entity.member.Member;

public interface MemberService {

    /**
     * 일반 회원 정보 등록 기능
     *
     * @param member
     * @return Long : new member ID
     */
    Long saveMemberInfo(Member member);

    /**
     * 최초 회원 가입자의 채식 유형 저장 기능
     *
     * @param memberId
     * @param vegetarianType
     * @return
     */
    Member saveTypeOfNewMember(Long memberId, VegetarianTypeInputDto vegetarianType);

    /**
     * 회원 정보 수정 기능
     *
     * @param email
     * @param member
     * @return MemberResponseDto
     */
    Member updateMember(Member member);

    /**
     * 개별 회원 정보 조회 기능
     *
     * @param email
     * @return MemberResponseDto
     */
    Member findMemberByEmail(String email);

    /**
     * 회원 정보 삭제 기능
     *
     * @param email
     */
    void removeMember(String email);
}
