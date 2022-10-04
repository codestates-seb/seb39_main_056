package com.noterror.app.infra.oauth2;

import com.noterror.app.api.domain.entity.member.Member;
import com.noterror.app.api.domain.member.memberService.MemberService;
import com.noterror.app.infra.auth.CustomAuthorityUtils;
import com.noterror.app.infra.auth.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * OAuth2 인증에 성공 시 호출되는 핸들러
 * : JWT 생성하고, Frontend 쪽으로 JWT 를 전송하기 위해 Redirect
 */
@RequiredArgsConstructor
public class OAuth2MemberSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        var oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = String.valueOf(oAuth2User.getAttributes().get("email"));
        String memberName = String.valueOf(oAuth2User.getAttributes().get("fullName"));
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities(email);

        saveMember(email, memberName);

        redirect(request,
                response,
                email,
                memberName,
                authorities);
    }

    private void saveMember(String email, String memberName) {
        Member member = new Member(email, memberName);
        memberService.saveMemberBySocial(member);
    }

    private void redirect(HttpServletRequest request,
                          HttpServletResponse response,
                          String email,
                          String memberName,
                          List<GrantedAuthority> authorities) throws IOException {

        String accessToken = delegateAccessToken(email, memberName, authorities);
        String refreshToken = delegateRefreshToken(email);

        String uri = createURI(accessToken, refreshToken).toString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }

    private String delegateAccessToken(String email,
                                       String memberName,
                                       List<GrantedAuthority> authorities) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("memberName", memberName);
        claims.put("roles", authorities);

        String subject = email;
        Date expiration = jwtTokenizer.getTokenExpiration(
                jwtTokenizer.getAccessTokenExpirationMinutesInfo());
        String secretKey = jwtTokenizer.encodeBase64SecretKey(
                jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims,
                subject, expiration, secretKey);
        return accessToken;
    }

    private String delegateRefreshToken(String email) {
        String subject = email;
        Date expiration = jwtTokenizer.getTokenExpiration(
                jwtTokenizer.getRefreshTokenExpirationMinutesInfo());
        String secretKey = jwtTokenizer.encodeBase64SecretKey(
                jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(
                subject, expiration, secretKey);
        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .path("/receive-token.html")
                .queryParam(String.valueOf(queryParams))
                .build()
                .toUri();
    }
}
