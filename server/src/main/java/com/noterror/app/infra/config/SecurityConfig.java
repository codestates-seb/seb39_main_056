package com.noterror.app.infra.config;

import com.noterror.app.infra.auth.CustomAuthorityUtils;
import com.noterror.app.infra.filter.JwtAuthenticationFilter;
import com.noterror.app.infra.filter.JwtVerificationFilter;
import com.noterror.app.infra.handler.MemberAccessDeniedHandler;
import com.noterror.app.infra.handler.MemberAuthFailureHandler;
import com.noterror.app.infra.handler.MemberAuthSuccessHandler;
import com.noterror.app.infra.handler.MemberAuthenticationEntryPoint;
import com.noterror.app.infra.jwt.JwtTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final AppConfig appConfig;

    public SecurityConfig(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils, AppConfig appConfig) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.appConfig = appConfig;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(
                        authorize -> authorize
                                .antMatchers(HttpMethod.GET, "/members/**").hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.PUT, "/members/**").hasAnyRole("USER", "ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/members/**").hasAnyRole("USER", "ADMIN")
                                .antMatchers("/admin/**").hasRole("ADMIN")
                                .antMatchers("/cart/**").hasAnyRole("USER", "ADMIN")
                                .antMatchers("/orders/**").hasAnyRole("USER", "ADMIN")
                                .anyRequest().permitAll()
                );
        return http.build();
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder.addFilter(appConfig.corsFilter())
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }

}