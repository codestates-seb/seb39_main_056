package com.noterror.app.api.domain.member.memberService;

import com.noterror.app.api.domain.entity.VegetarianType;
import com.noterror.app.api.domain.entity.member.Member;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.dto.SignUpDto;
import com.noterror.app.api.domain.member.dto.UpdateInfoDto;
import com.noterror.app.api.domain.member.dto.VegetarianTypeInputDto;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.domain.vegetarianType.repository.VegetarianTypeRepository;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.noterror.app.api.global.exception.ExceptionCode.MEMBER_NOT_FOUND;
import static com.noterror.app.api.global.exception.ExceptionCode.TYPE_BAD_REQUEST;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final VegetarianTypeRepository vegetarianTypeRepository;
    private final PasswordEncoder passwordEncoder;

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
    public MemberResponseDto saveTypeOfNewMember(Long memberId, VegetarianTypeInputDto vegetarianType) {
        Member member = findExistsMember(memberId);
        VegetarianType type = getVegetarianTypeInDb(vegetarianType.getVegetarianType());
        member.setVegetarianType(type);
        return new MemberResponseDto(member);
    }

    @Override
    @Transactional
    public MemberResponseDto updateMember(Long memberId, UpdateInfoDto updateInfoDto) {
        Member findMember = findExistsMember(memberId);
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
    public void removeMember(Long memberId) {
        Member findMember = findExistsMember(memberId);
        memberRepository.delete(findMember);
    }

    public Member findExistsMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
    }

    private Member proceedSignUp(SignUpDto signUpDto) {
        Member newMember = new Member();
        String email = signUpDto.getEmail();
        if (verifyExistsEmail(email)) {
            newMember = findMemberByEmail(email);
            newMember.proceedSocialSignUp(signUpDto);
        } else {
            String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
            newMember.proceedGeneralSignUp(signUpDto, encodedPassword);
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
        return vegetarianTypeRepository.findById(inputText)
                .orElseThrow(()-> new BusinessLogicException(TYPE_BAD_REQUEST));
    }
}

