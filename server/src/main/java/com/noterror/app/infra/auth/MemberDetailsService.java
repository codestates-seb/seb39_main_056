<<<<<<< HEAD:server/src/main/java/com/noterror/app/infra/auth/MemberDetailsService.java
package com.noterror.app.infra.auth;

import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.exception.BusinessLogicException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.noterror.app.api.global.exception.ExceptionCode.MEMBER_NOT_FOUND;

@Component
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    public MemberDetailsService(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
        return new MemberDetails(findMember, authorityUtils);
    }
}
=======
package com.noterror.app.infra.auth;

import com.noterror.app.api.domain.entity.member.Member;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.global.exception.BusinessLogicException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.noterror.app.api.global.exception.ExceptionCode.MEMBER_NOT_FOUND;

@Component
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;


    public MemberDetailsService(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
        return new MemberDetails(findMember, authorityUtils);
    }


}
>>>>>>> 3ccc649b13e31b48388eeebf66b5113d94af03ad:server/src/main/java/com/noterror/app/infra/auth/MemberDetailService.java
