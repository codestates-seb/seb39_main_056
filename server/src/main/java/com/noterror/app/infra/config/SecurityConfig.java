package com.noterror.app.infra.config;

import com.noterror.app.infra.auth.AuthFailureHandler;
import com.noterror.app.infra.auth.AuthSuccessHandler;
import com.noterror.app.infra.auth.JwTokenizer;
import com.noterror.app.infra.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static java.util.Arrays.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwTokenizer jwTokenizer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() // 동일 출처로부터 들어오는 request 만 페이지 렌더링
                .and()
                .csrf().disable() // CSRF 공격 방지 비활성화(403 에러 방지)
                .cors(withDefaults()) // corsConfigurationSource 이름으로 등록된 Bean 이용 -> Cors Filter 적용 -> cors 처리 -> merge 이후 컨트롤러의 cors 애너테이션 제거
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigure())
                .and()
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll()); // 모든 http request 허용 -> 추후 제거
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
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","DELETE","OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 url 적용
        return source;
    }

    public class CustomFilterConfigure extends AbstractHttpConfigurer<CustomFilterConfigure, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new AuthFailureHandler());
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new AuthSuccessHandler());
            builder.addFilter(jwtAuthenticationFilter);
        }
    }
}
