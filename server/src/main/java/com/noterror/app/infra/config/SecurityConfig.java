package com.noterror.app.infra.config;

import com.noterror.app.api.domain.member.memberService.MemberService;
import com.noterror.app.infra.auth.CustomAuthorityUtils;
import com.noterror.app.infra.filter.JwtAuthenticationFilter;
import com.noterror.app.infra.filter.JwtVerificationFilter;
import com.noterror.app.infra.handler.MemberAccessDeniedHandler;
import com.noterror.app.infra.handler.MemberAuthenticationEntryPoint;
import com.noterror.app.infra.handler.OAuth2MemberSuccessHandler;
import com.noterror.app.infra.jwt.JwtTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;

    public SecurityConfig(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils, MemberService memberService) {
        this.jwtTokenizer = jwtTokenizer;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(
                        authorize -> authorize
                                .antMatchers("/members").permitAll()
                                .antMatchers(HttpMethod.GET,"/products").permitAll()
                                .antMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // 추가
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            builder.addFilter(jwtAuthenticationFilter);
        }
    }


}
