package com.noterror.app.api.domain.user.controller;

import com.noterror.app.api.domain.user.dto.IdTokenRequestDto;
import com.noterror.app.api.domain.user.service.OAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/oauth/login")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuth2UserService oauth2UserService;

    @PostMapping("/google")
    public ResponseEntity LoginWithGoogleOauth2(
            @RequestBody IdTokenRequestDto requestBody,
            HttpServletResponse response) {
        String authToken = oauth2UserService.loginOAuthGoogle(requestBody);
        final ResponseCookie cookie = ResponseCookie.from("AUTH-TOKEN", authToken)
                .httpOnly(true)
                .maxAge(7 * 24 * 3600)
                .path("/")
                .secure(false)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok().build();
    }
}
