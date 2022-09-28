package com.noterror.app.infra.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.member.dto.GeneralLoginForm;
import com.noterror.app.api.domain.member.dto.MemberRequestDto;
import com.noterror.app.infra.auth.JwTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwTokenizer jwTokenizer;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();
        GeneralLoginForm generalLoginForm = objectMapper.readValue(request.getInputStream(), GeneralLoginForm.class);

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(generalLoginForm.getEmail(), generalLoginForm.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        Member member = (Member) authResult.getPrincipal();

        String accessToken = delegateAccessToken(member);
        String refreshToken = delegateRefreshToken(member);

        response.setHeader("Authorization", "Bearer" + accessToken);
        response.setHeader("Refresh", refreshToken);
    }

    private String delegateAccessToken(Member member) {

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("email", member.getEmail());
        claims.put("roles", member.getRoles());

        String subject = member.getMemberName();
        Date expiration = jwTokenizer.getTokenExpiration(jwTokenizer.getAccessTokenExpirationMinutesInfo());
        String secretKey = jwTokenizer.encodeBase64SecretKey(jwTokenizer.getSecretKey());

        return jwTokenizer.generateAccessToken(claims, subject, expiration, secretKey);
    }

    private String delegateRefreshToken(Member member) {
        String subject = member.getMemberName();
        Date expiration = jwTokenizer.getTokenExpiration(jwTokenizer.getRefreshTokenExpirationMinutesInfo());
        String secretKey = jwTokenizer.encodeBase64SecretKey(jwTokenizer.getSecretKey());

        return jwTokenizer.generateRefreshToken(subject, expiration, secretKey);
    }
}
