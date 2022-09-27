package com.noterror.app.api.domain.user.userService;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.dto.MemberRequestDto;


public interface MemberService {
     Member create(MemberRequestDto memberRequestDto);
}
