package com.creavispace.project.member.service;

import com.creavispace.project.member.Member;
import com.creavispace.project.member.MemberUpdateDto;
import java.util.Optional;

public interface MemberService {
    Member save(Member member);

    void update(Long memberId, MemberUpdateDto updateParam);

    Optional<Member> findByEmail(String email);

    Optional<Member> findById(Long memberId);

    boolean emailExsists(String memberEmail);
}
