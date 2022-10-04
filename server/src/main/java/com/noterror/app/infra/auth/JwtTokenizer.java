package com.noterror.app.infra.auth;

import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static com.noterror.app.api.global.exception.ExceptionCode.MEMBER_EXPIRED_TOKEN;

/**
 * 담당자 : 강시혁
 *
 * @Class JWT 를 생성하고 검증하는 역할 수행
 */
@Component
public class JwtTokenizer {

    @Getter
    @Value("${jwt.key.secret-key")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutesInfo; // AccessToken 만료 시간 정보

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutesInfo; // RefreshToken 만료 시간 정보

    // Plain Text 형태인 Secret Key 의 byte[]를 Base64 형식의 문자열로 인코딩
    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 최초 생성
    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncoderSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncoderSecretKey);

        return Jwts.builder()
                .setClaims(claims) // 인증된 사용자와 관련된 정보 추가
                .setSubject(subject) // JWT 제목
                .setIssuedAt(Calendar.getInstance().getTime()) // 발행일자
                .setExpiration(expiration) // 만료일시
                .signWith(key) // 서명
                .compact(); // JWT 생성과 직렬화
    }

    // Refresh Token 생성
    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    // Secret Key 생성(HMAC 알고리즘 적용)
    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }

    // 검증 후, Claims 반환
    public Jws<Claims> getClaims(String jws, String encodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(encodedSecretKey);

        Jws<Claims> claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jws);
        } catch (ExpiredJwtException e) {
            throw new BusinessLogicException(MEMBER_EXPIRED_TOKEN);
        }
        return claims;
    }

    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }

    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();
        return expiration;
    }

}
