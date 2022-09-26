package com.noterror.app.api.domain.user.controller;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.dto.IdTokenRequestDto;
import com.noterror.app.api.domain.user.mapper.MemberMapper;
import com.noterror.app.api.domain.user.service.OAuth2MemberService;
import com.noterror.app.api.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuth2MemberService oauth2MemberService;
    private final MemberService memberService;
    private final MemberMapper mapper;

    @PostMapping("/login/google")
    public ResponseEntity LoginWithGoogleOauth2(
            @RequestBody IdTokenRequestDto requestBody,
            HttpServletResponse response) {
        String authToken = oauth2MemberService.loginOAuthGoogle(requestBody);
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
        Member member = memberService.findUser(Long.valueOf(principal.getName()));
        return new ResponseEntity(
                mapper.socialInfoInResponseToMember(member),
                HttpStatus.OK
        );
    }


}
