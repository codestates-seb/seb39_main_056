package com.noterror.app.api.domain.member.memberService;

import com.noterror.app.api.domain.member.dto.MemberRequestDto;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;

public interface MemberService {

     /**
      * 회원 정보 등록 기능
      * @param request
      * @return MemberResponseDto
      */
     MemberResponseDto saveMemberInfo(MemberRequestDto request);

     /**
      * 회원 정보 수정 기능
      * @param id
      * @param request
      * @return MemberResponseDto
      */
     MemberResponseDto updateMember(Long id, MemberRequestDto request);

     /**
      * 개별 회원 정보 조회 기능
      * @param id
      * @return MemberResponseDto
      */
     MemberResponseDto findMember(Long id);

     /**
      * 회원 정보 삭제 기능
      * @param id
      */
     void removeMember(Long id);
}
