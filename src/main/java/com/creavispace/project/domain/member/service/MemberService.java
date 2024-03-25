package com.creavispace.project.domain.member.service;

import com.creavispace.project.domain.member.dto.response.MemberResponseDto;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.mypage.dto.request.MyPageModifyRequestDto;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member save(Member member);

    void update(Member member, MyPageModifyRequestDto updateParam);

    Optional<Member> findByEmail(String email);

    MemberResponseDto findById(Long memberId);

    Optional<Member> findByEmailAndNameAndLoginId(String email, String name, String loginId);

    boolean emailExsists(String memberEmail);

    List<Member> findAllMembers();

    String login(String memberEmail, String loginType, Long memberId);

    Optional<Member> findByEmailAndLoginTypeAndMemberId(String memberEmail, String loginType, Long memberId);

    List<MemberResponseDto> findByMemberNicknameOrIdTagContaining(String search);
    Optional<Member> findByLoginId(String loginId);
}