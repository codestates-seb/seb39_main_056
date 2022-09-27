package com.noterror.app.api.domain.member.mapper;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.member.dto.MemberRequestDto;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberRequestDtoToMember(MemberRequestDto request);
    MemberResponseDto memberToMemberResponseDto(Member member);
}
