package com.noterror.app.api.domain.user.controller;

import com.noterror.app.api.domain.entity.User;
import com.noterror.app.api.domain.user.dto.IdTokenRequestDto;
import com.noterror.app.api.domain.user.dto.SocialInfoInResponse;
import com.noterror.app.api.domain.user.mapper.UserMapper;
import com.noterror.app.api.domain.user.service.OAuth2UserService;
import com.noterror.app.api.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyleContext;
import java.security.Principal;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuth2UserService oauth2UserService;
    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping("/login/google")
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

    @GetMapping("/user/info")
    public ResponseEntity getUserInfo(Principal principal) {
        User user = userService.findUser(Long.valueOf(principal.getName()));
        return new ResponseEntity(
                mapper.socialInfoInResponseToUser(user),
                HttpStatus.OK
        );
    }


}
