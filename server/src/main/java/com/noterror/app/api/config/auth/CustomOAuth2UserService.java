package com.noterror.app.api.config.auth;

import com.noterror.app.api.config.auth.dto.OAuthAttributes;
import com.noterror.app.api.config.auth.dto.SessionUser;
import com.noterror.app.api.domain.entity.User;
import com.noterror.app.api.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    //OAuth2UserService 를 통해 UserRequest 정보를 빼내올 수 있음
    //OAuth2UserRequest 에는 ClientRegistration, accessToken 등이 있음
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService(); //DefaultOAuth2UserService()는 OAuth2UserService의 구현체
        OAuth2User oAuth2User = delegate.loadUser(userRequest); //loadUser 메서드를 활용하여 유저 정보를 oauth2User에 넣음

        String registrationId = userRequest  //로그인 진행중인 서비스를 구분하는 코드 (구글임을 명시) 를 가져오는 과정
                .getClientRegistration()
                .getRegistrationId();

        String userNameAttributeName = userRequest //Oauth2로그인 진행 시, 키가 되는 필드 값 = PK (구글은 기본 "sub") 을 가져오는 과정
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
                                                                            //oAuth2User.getAttributes를 하면 sub, name, given_name, email 등의 정보를 가져옴

       User user = saveOrUpdate(attributes); //저장 정보 업데이트 메서드

        httpSession.setAttribute("user", new SessionUser(user));    //sessionUser은 세션에 사용자 정보를 저장하기 위한 Dto 클래스
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
