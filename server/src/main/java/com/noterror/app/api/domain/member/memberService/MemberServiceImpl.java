package com.noterror.app.api.domain.member.memberService;

import com.noterror.app.api.domain.entity.VegetarianType;
import com.noterror.app.api.domain.entity.member.Member;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.domain.member.dto.VegetarianTypeDto;
import com.noterror.app.api.domain.member.mapper.MemberMapper;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.vegetarianType.repository.VegetarianTypeRepository;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final VegetarianTypeRepository vegetarianTypeRepository;
    private final MemberMapper mapper;

    @Override
    @Transactional
    public void saveMemberBySocial(Member member) {
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public Long saveMemberInfo(SignUpDto signUpDto) {
        Member newMember = proceedSignUp(signUpDto);
        memberRepository.save(newMember);
        return newMember.getMemberId();
    }

    @Override
    @Transactional
    public MemberResponseDto saveTypeOfNewMember(Long memberId, VegetarianTypeDto vegetarianType) {
        Member member = findExistsMember(memberId);
        VegetarianType type = getVegetarianTypeInDb(vegetarianType.getVegetarianType());
        member.setVegetarianType(type);
        return new MemberResponseDto(member);
    }

    @Override
    public MemberResponseDto updateMember(Long memberId, UpdateInfoDto updateInfoDto) {
        Member findMember = findExistsMember(memberId);
        VegetarianType type = getVegetarianTypeInDb(updateInfoDto.getVegetarianType());
        findMember.updateMemberInfo(updateInfoDto, type);
        Member updateMember = memberRepository.save(findMember);
        return mapper.memberToMemberResponseDto(updateMember);
    }

    @Override
    public MemberResponseDto findMember(String email) {
        Member existsMember = findMemberByEmail(email);
        return mapper.memberToMemberResponseDto(existsMember);
    }

    @Override
    @Transactional
    public void removeMember(Long memberId) {
        Member findMember = findExistsMember(memberId);
        memberRepository.delete(findMember);
    }

    public Member findExistsMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    private Member proceedSignUp(SignUpDto signUpDto) {

        Member newMember = new Member();
        String email = signUpDto.getEmail();

        if (verifyExistsEmail(email)) {
            newMember = findMemberByEmail(email);
            newMember.proceedSocialSignUp(signUpDto);
        } else {
            newMember.proceedGeneralSignUp(signUpDto);
        }
        return newMember;
    }

    private Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email).get();
    }

    private boolean verifyExistsEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    private VegetarianType getVegetarianTypeInDb(String inputText) {
        return vegetarianTypeRepository.findByVegetarianTypeName(inputText).get();
    }
}

