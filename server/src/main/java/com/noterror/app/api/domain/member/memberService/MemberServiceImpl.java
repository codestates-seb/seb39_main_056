package com.noterror.app.api.domain.member.memberService;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.member.dto.MemberRequestDto;
import com.noterror.app.api.domain.member.dto.MemberResponseDto;
import com.noterror.app.api.domain.member.mapper.MemberMapper;
import com.noterror.app.api.domain.member.repository.MemberRepository;
import com.noterror.app.api.global.exception.BusinessLogicException;
import com.noterror.app.api.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper mapper;

    @Override
    public MemberResponseDto saveMemberInfo(MemberRequestDto memberRequestDto) {
        Member mappingMember = mapper.memberRequestDtoToMember(memberRequestDto);
        Member newMember = memberRepository.save(mappingMember);
        return mapper.memberToMemberResponseDto(newMember);
    }

    @Override
    public MemberResponseDto updateMember(Long memberId, MemberRequestDto memberRequestDto) {
        Member findMember = findExistsMember(memberId);
        findMember.updateMemberInfo(memberRequestDto);
        Member updateMember = memberRepository.save(findMember);
        return mapper.memberToMemberResponseDto(updateMember);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto findMember(Long memberId) {
        Member existsMember = findExistsMember(memberId);
        return mapper.memberToMemberResponseDto(existsMember);
    }

    @Override
    public void removeMember(Long memberId) {
        Member findMember = findExistsMember(memberId);
        memberRepository.delete(findMember);
    }

    public Member findExistsMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}


