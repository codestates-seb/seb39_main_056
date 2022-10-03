package com.noterror.app.infra.filter;

import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import com.noterror.app.infra.auth.CustomAuthorityUtils;
import com.noterror.app.infra.auth.JwtTokenizer;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JWT 검증 필터
 */
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            Map<String, Object> claims = verifyJws(request);
            setAuthenticationToContext(claims);
        } catch (ExpiredJwtException ee) {
            BusinessLogicException exception = new BusinessLogicException(ExceptionCode.MEMBER_EXPIRED_TOKEN);
            request.setAttribute("exception", exception);
        } catch (Exception e) {
            BusinessLogicException exception = new BusinessLogicException(ExceptionCode.MEMBER_LOGIN_FAIL);
            request.setAttribute("exception", exception);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");
        return authorization == null ||
                !authorization.startsWith("Bearer");
    }

    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ","");
        String secretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, secretKey).getBody();

        return claims;
    }

    // SecurityContext 에 Authentication 객체 저장
    // REST API 는 Stateless 인데? 왜?
    private void setAuthenticationToContext(Map<String,Object> claims) {
        String username = (String) claims.get("username");
        List roles = authorityUtils.createAuthorities((List) claims.get("roles"));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, roles);
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
