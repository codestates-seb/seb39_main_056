
package com.noterror.app.api.domain.member.service;

import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.domain.member.dto.VegetarianTypeInputDto;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.infra.auth.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.noterror.app.api.global.exception.ExceptionCode.MEMBER_EXISTS;
import static com.noterror.app.api.global.exception.ExceptionCode.MEMBER_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    @Transactional
    public Long saveMemberInfo(Member signUpInfo) {
        verifyExistsEmail(signUpInfo.getEmail());
        insertAuthInfo(signUpInfo);
        Member newMember = memberRepository.save(signUpInfo);
        return newMember.getMemberId();
    }

    @Override
    @Transactional
    public Member saveTypeOfNewMember(Long memberId, VegetarianTypeInputDto request) {
        Member member = findMemberById(memberId);
        member.setVegetarianType(request.getVegetarianType());
        return member;
    }

    @Override
    @Transactional
    public Member updateMember(String currentUserEmail, Member updateInfo) {
        Member findMember = findMemberByEmail(currentUserEmail);
        findMember.updateMemberInfo(updateInfo);
        return memberRepository.save(findMember);
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
    }

    @Override
    @Transactional
    public void removeMember(String email) {
        Member findMember = findMemberByEmail(email);
        memberRepository.delete(findMember);
    }

    private void insertAuthInfo(Member newMember) {
        List<String> roles = authorityUtils.createRoles(newMember.getEmail());
        String encodedPassword = passwordEncoder.encode(newMember.getPassword());
        newMember.insertAuthInfo(roles, encodedPassword);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(MEMBER_EXISTS);
    }
}
