package com.noterror.app.api.domain.user.repository;

import com.noterror.app.api.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String Email); // 회원가입 시 중복된 회원 이메일로 검사
}
