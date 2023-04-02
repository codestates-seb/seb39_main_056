package com.noterror.app.infra.filter;

import com.noterror.app.api.global.advice.GlobalExceptionAdvice;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import com.noterror.app.api.global.exception.response.ErrorResponse;
import com.noterror.app.infra.auth.CustomAuthorityUtils;
import com.noterror.app.infra.jwt.JwtTokenizer;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Request Header 에 포함된 JWT 검증 작업 수행
 */
public class JwtVerificationFilter extends OncePerRequestFilter {

    // JWT 를 검증하고 Claims 를 얻는데 시용
    private final JwtTokenizer jwtTokenizer;
    // JWT 검증에 성공하면 Authentication 객체에 넣어줄 권한을 생성
    private final CustomAuthorityUtils authorityUtils;

    private final Logger logger = LoggerFactory.getLogger(JwtVerificationFilter.class);

    public JwtVerificationFilter(JwtTokenizer jwtTokenizer
            , CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            Map<String, Object> claims = verifyJws(request);
            setAuthenticationToContext(claims);
        } catch (NullPointerException e) {
            BusinessLogicException exception = new BusinessLogicException(ExceptionCode.MEMBER_EXPIRED_TOKEN);
            GlobalExceptionAdvice handler = new GlobalExceptionAdvice();
            handler.handleBusinessLogicException(exception);
        }

        filterChain.doFilter(request, response);
    }

    // 특정 조건에 부합하면 해당 Filter 의 동작을 수행하지 않고 다음 Filter 로 건너뜀
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");

        return authorization == null || !authorization.startsWith("Bearer");
    }

    // JWT 검증
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        // jwt : JSON Web Token Signed
        // header 에서 JWT를 얻음
        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = null;

        try {
            claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }

        return claims;
    }

    // Authentication 객체를 SecurityContext 에 저장
    private void setAuthenticationToContext(Map<String, Object> claims) {
        String username = (String) claims.get("username");
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List) claims.get("roles"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
