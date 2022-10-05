package com.noterror.app.infra;


import com.noterror.app.infra.jwt.JwtTokenizer;
import io.jsonwebtoken.io.Decoders;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtTokenizerTest {
    private static JwtTokenizer jwtTokenizer;
    private String secretKey;
    private String base64EncodedSecretKey;

    @BeforeAll
    public void init() {
        jwtTokenizer = new JwtTokenizer();
        secretKey = "kevin1234123412341234123412341234";
        base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(secretKey);
    }
    
    @Test
    @DisplayName("Base64 encoded 키 출력 테스트")
    void encodeBase64SecretKeyTest() throws Exception {
        System.out.println(base64EncodedSecretKey); // encoded "a2V2aW4xMjM0MTIzNDEyMzQxMjM0MTIzNDEyMzQxMjM0"
        assertThat(secretKey, is(new String(Decoders.BASE64.decode(base64EncodedSecretKey))));
    }

    @Test
    @DisplayName("ACCESS 토큰 발급 테스트")
    void generateAccessTokenTest() throws Exception {
        //given
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId",1);
        claims.put("roles", List.of("USER"));

        String subject = "test access token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        Date expiration = calendar.getTime();

        //when
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        //then
        System.out.println(accessToken);
        assertThat(accessToken, notNullValue());
    }

    @Test
    @DisplayName("REFRESH 토큰 발급 테스트")
    public void generatedRefreshTokenTest() {
        //given
        String subject = "test refresh token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        Date expiration = calendar.getTime();

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        assertThat(refreshToken, notNullValue());
    }

    @Test
    @DisplayName("jwt 가 정상적으로 검증되었을 때 - does not throw any Exception")
    public void verifySignatureTest() {
        String accessToken = getAccessToken(Calendar.MINUTE, 10);
        assertDoesNotThrow(
                () -> jwtTokenizer.getClaims(accessToken, base64EncodedSecretKey));
    }

/*    @Test
    @DisplayName("expired 만료됨으로 인한 검증 실패 - throw '만료된 토큰입니다.' when jws verify")
    void verifyExpirationTest() throws Exception {
        String accessToken = getAccessToken(Calendar.SECOND, 1);
        assertDoesNotThrow(() -> jwtTokenizer.getClaims(accessToken, base64EncodedSecretKey));

        TimeUnit.MILLISECONDS.sleep(1500);

        assertThrows(BusinessLogicException.class,
                ()-> jwtTokenizer.getClaims(accessToken, base64EncodedSecretKey));

    }*/

    private String getAccessToken(int timeUnit, int timeAmount) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId",1);
        claims.put("roles", List.of("USER"));

        String subject = "test access token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(timeUnit, timeAmount);
        Date expiration = calendar.getTime();
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

}
