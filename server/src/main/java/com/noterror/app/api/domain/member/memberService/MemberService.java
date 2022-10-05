package com.noterror.app.api.domain.member.memberService;

import com.noterror.app.api.domain.entity.member.Member;
import com.noterror.app.api.domain.member.dto.*;

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
      * @param vegetarianType
      * @return
      */
     MemberResponseDto saveTypeOfNewMember(Long memberId, VegetarianTypeInputDto vegetarianType);

     /**
      * 회원 정보 수정 기능
      * @param email
      * @param updateInfoDto
      * @return MemberResponseDto
      */
     MemberResponseDto updateMember(String email, UpdateInfoDto updateInfoDto);

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
     void removeMember(String email);
}
