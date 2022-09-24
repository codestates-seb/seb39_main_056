package com.noterror.app.api.config.auth.dto;

import com.noterror.app.api.domain.entity.Role;
import com.noterror.app.api.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * OAuthAttributes 는 OAuth2UserService를 통해 가져온 소셜 유저의 Attribute를 담은 클래스
 * 다른 소셜 로그인에서도 이 클래스를 사용할 수 있음
 */
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey,
                           String name,
                           String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) { //Oauth2User 에서 반환하는 사용자 정보는 Map 이기 때문에 값 하나하나를 변환
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() { //User 엔티티를 생성 ! OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때!
        return User.builder()
                .userName(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}

