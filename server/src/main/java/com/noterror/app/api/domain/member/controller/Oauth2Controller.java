package com.noterror.app.api.domain.member.controller;

import com.noterror.app.api.domain.entity.member.Member;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * 담당자 : 강시혁
 * SCOPE : 인증
 * 대상 : OAUTH2, LOGIN, SECURITY
 */
@Controller
@RequiredArgsConstructor
public class Oauth2Controller {

    private MemberRepository memberRepository;

    @GetMapping("/google")
    public ResponseEntity getMemberInfo(Principal principal) {

        String email = principal.getName();

        Member member = memberRepository.findByEmail(email).get();

        return ResponseEntity.ok(member);
    }
}
