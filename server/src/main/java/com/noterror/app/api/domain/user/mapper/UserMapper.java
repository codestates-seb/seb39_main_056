package com.noterror.app.api.domain.user.mapper;

import com.noterror.app.api.domain.entity.User;
import com.noterror.app.api.domain.user.dto.SocialInfoInResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

     SocialInfoInResponse socialInfoInResponseToUser(User user);

}
