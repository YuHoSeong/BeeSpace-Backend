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
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    @Override
    public Member save(Member member) {
        System.out.println(repository.existsByMemberEmail(member.getMemberEmail()));
        return repository.save(member);
    }

    @Override
    public void update(Long memberId, MemberUpdateDto updateParam) {
        Member member = repository.findById(memberId).orElseThrow();
        member.setMemberNickname(updateParam.getMemberNickname());
        member.setMemberPassword(updateParam.getMemberPassword());
        repository.save(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return repository.findById(memberId);
    }

    @Override
    public boolean emailExsists(String memberEmail) {
        return repository.existsByMemberEmail(memberEmail);
    }

    @Override
    public List<Member> findAllMembers() {
        return repository.findAll();
    }
}
