
package com.noterror.app.api.domain.member.service;

import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.domain.member.dto.VegetarianTypeInputDto;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.vegetarian.repository.VegetarianTypeRepository;
import com.noterror.app.api.entity.VegetarianType;
import com.noterror.app.api.entity.member.Member;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.infra.auth.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


import static com.noterror.app.api.global.exception.ExceptionCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final VegetarianTypeRepository vegetarianTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    @Transactional
    public Long saveMemberInfo(SignUpDto signUpDto) {
        Member newMember = proceedSignUp(signUpDto);
        Member createMember = memberRepository.save(newMember);
        return createMember.getMemberId();
    }

    @Override
    @Transactional
    public MemberResponseDto saveTypeOfNewMember(Long memberId, VegetarianTypeInputDto vegetarianType) {
        Member member = findMemberById(memberId);
        VegetarianType type = getVegetarianTypeInDb(vegetarianType.getVegetarianType());
        member.setVegetarianType(type);
        return new MemberResponseDto(member);
    }

    @Override
    @Transactional
    public MemberResponseDto updateMember(String email, UpdateInfoDto updateInfoDto) {
        Member findMember = findMemberByEmail(email);
        VegetarianType type = getVegetarianTypeInDb(updateInfoDto.getVegetarianType());
        findMember.updateMemberInfo(updateInfoDto, type);
        Member updateMember = memberRepository.save(findMember);
        return new MemberResponseDto(updateMember);
    }

    @Override
    public MemberResponseDto findMember(String email) {
        Member existsMember = findMemberByEmail(email);
        return new MemberResponseDto(existsMember);
    }

    @Override
    @Transactional
    public void removeMember(String email) {
        Member findMember = findMemberByEmail(email);
        memberRepository.delete(findMember);
    }


    private Member proceedSignUp(SignUpDto signUpDto) {

        String email = signUpDto.getEmail();
        verifyExistsEmail(email);

        List<String> roles = authorityUtils.createRoles(email);
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());

        Member newMember = new Member();
        newMember.proceedGeneralSignUp(signUpDto, roles, encodedPassword);

        return newMember;
    }

    private Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
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

    private VegetarianType getVegetarianTypeInDb(String inputText) {
        return vegetarianTypeRepository.findById(inputText)
                .orElseThrow(() -> new BusinessLogicException(TYPE_BAD_REQUEST));
    }
}
