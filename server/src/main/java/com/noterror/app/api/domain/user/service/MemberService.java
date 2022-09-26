package com.noterror.app.api.domain.user.service;

import com.noterror.app.api.domain.entity.Member;
import com.noterror.app.api.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findUser(Long userId) {
        return memberRepository.findById(userId)
                .orElseThrow(()->  new NullPointerException("회원을 조회할 수 없습니다."));
    }
}
