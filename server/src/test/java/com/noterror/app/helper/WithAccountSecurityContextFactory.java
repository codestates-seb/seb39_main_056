package com.noterror.app.helper;

import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.VegetarianTypeInputDto;
import com.noterror.app.api.domain.member.service.MemberService;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.infra.auth.CustomAuthorityUtils;
import com.noterror.app.infra.auth.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    @Autowired
    private MemberService memberService;

    @Override
    public SecurityContext createSecurityContext(WithAccount withAccount) {

        String memberName = withAccount.value();
        String email = memberName + "056" + "@" + memberName + ".com";

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setEmail(email);
        signUpDto.setPassword("12345678");
        signUpDto.setMemberName(email);
        signUpDto.setPhone("010-1111-1111");
        signUpDto.setZipCode("63336");
        signUpDto.setCity("test");
        signUpDto.setDetailAddress("test");
        Member signUpInfo = signUpDto.toEntity();

        Long newId = memberService.saveMemberInfo(signUpInfo);
        Member newMember = memberService.saveTypeOfNewMember(newId, new VegetarianTypeInputDto("비건"));

        MemberDetails principal = new MemberDetails(newMember, new CustomAuthorityUtils());
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principal,
                        principal.getPassword(),
                        principal.getAuthorities());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        return context;
    }
}
