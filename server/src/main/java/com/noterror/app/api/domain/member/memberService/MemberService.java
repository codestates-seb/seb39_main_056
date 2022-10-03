package com.noterror.app.api.domain.member.memberService;

import com.noterror.app.api.domain.entity.member.Member;
import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.dto.VegetarianTypeDto;

public interface MemberService {

     /**
      * 일반 회원 정보 등록 기능
      * @param signUpDto
      * @return Long : ID of new member
      */
     Long saveMemberInfo(SignUpDto signUpDto);

     /**
      * 최초 회원 가입자의 채식 유형 저장 기능
      * @param memberId
      * @param vegetarianTypeInputDto
      * @return
      */
     MemberResponseDto saveTypeOfNewMember(Long memberId, VegetarianTypeDto vegetarianTypeInputDto);

     /**
      * 회원 정보 수정 기능
      * @param id
      * @param updateInfoDto
      * @return MemberResponseDto
      */
     MemberResponseDto updateMember(Long id, UpdateInfoDto updateInfoDto);

     /**
      * 개별 회원 정보 조회 기능
      * @param email
      * @return MemberResponseDto
      */
     MemberResponseDto findMember(String email);

     /**
      * 회원 정보 삭제 기능
      * @param id
      */
     void removeMember(Long id);

     /**
      * 비회원 소셜 인증 후, 회원 데이터 db 저장
      * @param member
      */
     void saveMemberBySocial(Member member);
}
