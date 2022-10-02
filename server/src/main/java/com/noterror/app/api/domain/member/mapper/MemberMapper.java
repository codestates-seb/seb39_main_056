package com.noterror.app.api.domain.member.mapper;

import com.noterror.app.api.domain.entity.member.Member;
import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    MemberResponseDto memberToMemberResponseDto(Member member);
}
