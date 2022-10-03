package com.noterror.app.infra.config;

import com.noterror.app.api.domain.member.memberService.MemberService;
import com.noterror.app.infra.auth.CustomAuthorityUtils;
import com.noterror.app.infra.auth.JwtTokenizer;
import com.noterror.app.infra.auth.MemberAccessDeniedHandler;
import com.noterror.app.infra.auth.MemberAuthenticationEntryPoint;
import com.noterror.app.infra.filter.JwtVerificationFilter;
import com.noterror.app.infra.oauth2.OAuth2MemberSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.headers().frameOptions().sameOrigin() // 동일 출처로부터 들어오는 request 만 페이지 렌더링
                //.and()
                .csrf().disable() // CSRF 공격 방지 비활성화
                //.cors(withDefaults()) // corsConfigurationSource 이름으로 등록된 Bean 이용 -> Cors Filter 적용 -> cors 처리 -> merge 이후 컨트롤러의 cors 애너테이션 제거
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 생성하지 않도록 설정
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigure())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/products/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/products/*").permitAll()
                        .antMatchers(HttpMethod.POST, "*/members/**").permitAll()
                        .antMatchers(HttpMethod.DELETE, "*/members/**").permitAll()
                        .antMatchers(HttpMethod.PUT, "*/members/**").permitAll()
                        .anyRequest().permitAll())
                .oauth2Login(oauth2 -> oauth2.successHandler(
                        new OAuth2MemberSuccessHandler(
                                jwtTokenizer, authorityUtils, memberService
                        )
                ));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // CORS 정책
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 url 적용
        return source;
    }

    // Custom filter 적용
    public class CustomFilterConfigure extends AbstractHttpConfigurer<CustomFilterConfigure, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);
            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class); // (2)
        }
    }

}
