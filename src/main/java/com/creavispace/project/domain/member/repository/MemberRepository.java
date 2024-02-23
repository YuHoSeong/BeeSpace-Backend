package com.creavispace.project.domain.member.repository;

import com.creavispace.project.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMemberEmail(String email);
    Optional<Member> findByMemberEmail(String email);
    Optional<Member> findByMemberEmailAndMemberNameAndLoginId(String email, String loginId, String name);
}
