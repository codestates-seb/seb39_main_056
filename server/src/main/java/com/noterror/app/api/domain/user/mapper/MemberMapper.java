package com.noterror.app.api.domain.user.mapper;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.dto.SocialInfoInResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

     SocialInfoInResponse socialInfoInResponseToMember(Member member);

}
