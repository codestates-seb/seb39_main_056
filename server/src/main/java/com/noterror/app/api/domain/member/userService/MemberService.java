package com.noterror.app.api.domain.member.userService;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.member.dto.MemberPatchDto;
import com.noterror.app.api.domain.member.dto.MemberRequestDto;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;


public interface MemberService {
     MemberResponseDto createMember(MemberRequestDto memberRequestDto);
     MemberResponseDto updateMember(Long id, MemberPatchDto memberPatchDto);

     MemberResponseDto findMember(Long id);
}
