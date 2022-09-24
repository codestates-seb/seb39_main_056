package com.noterror.app.api.config.auth;

import com.noterror.app.api.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정 활성화
public class SecurityConfig extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable()  //h2 콘솔 사용을 위해 해당 옵션을 무시
                .and()
                .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점으로 이게 선언되어야만 antMatchers 옵션 사용가능
                .antMatchers("/", "/css/**","images/**","/js/**","/h2-console/***").permitAll() //권한 관리 대상 지정(url, http메소드별로 관리 가능) - 전체 권한 줌
                .antMatchers("api/v1/**").hasRole(Role.USER.name())
                .anyRequest() //설정된 값들 이외의 나머지 URL을 나타냄
                .authenticated() //나머지 url은 인증된 사용자만 가능함 !
                .and()
                .logout().logoutSuccessUrl("/") //로그아웃 기능에 대한 여러 설정의 진입점으로 로그아웃 성공시 "/" 주소로 이동
                .and()
                .oauth2Login() //Oauth2로그인 기능에 대한 설정의 진입점
                .userInfoEndpoint() //Oauth2로그인 성공 이후 사용자 정보를 가져올 때 설정을 담당
                .userService(customOAuth2UserService); //로그인 성공시 후속 조치를 진행할 userservice 인터페이스의 구현 등록 (리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자하는 기능 명시 가능)
    }                                                  //성공하면 customOauth2userService에서 진행하겠다는 의미
}
