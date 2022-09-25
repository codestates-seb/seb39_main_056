package com.noterror.app.api.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SocialInfoInResponse {

    private String username;
    private String email;
}
