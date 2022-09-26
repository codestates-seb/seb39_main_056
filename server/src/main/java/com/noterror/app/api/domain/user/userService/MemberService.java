package com.noterror.app.api.domain.user.userService;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.dto.MemberRegistrationRequestDto;


public interface MemberService {

     /** 회원 정보 저장 */
     Member create(MemberRegistrationRequestDto memberRegistrationRequestDto);

     /** 회원정보 삭제 (회원탈퇴)*/
     void delete(long memberId);
}
