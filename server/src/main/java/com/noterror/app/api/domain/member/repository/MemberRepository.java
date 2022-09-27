
package com.noterror.app.api.domain.member.repository;

import com.noterror.app.api.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String Email);
}

