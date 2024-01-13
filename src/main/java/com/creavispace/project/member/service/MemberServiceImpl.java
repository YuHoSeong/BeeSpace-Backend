package com.creavispace.project.member.service;

import com.creavispace.project.member.Member;
import com.creavispace.project.member.MemberUpdateDto;
import com.creavispace.project.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Member save(Member member) {
        System.out.println(memberRepository.existsByMemberEmail(member.getMemberEmail()));
        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public void update(Long memberId, MemberUpdateDto updateParam) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.setMemberNickname(updateParam.getMemberNickname());
        member.setMemberPassword(updateParam.getMemberPassword());
        memberRepository.save(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public boolean emailExsists(String memberEmail) {
        return memberRepository.existsByMemberEmail(memberEmail);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
