package com.creavispace.project.domain.member.service;

import com.creavispace.project.domain.member.dto.request.MemberSaveRequestDto;
import com.creavispace.project.domain.member.dto.response.MemberResponseDto;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.dto.request.MemberUpdateRequestDto;
import com.creavispace.project.domain.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public MemberSaveRequestDto save(MemberSaveRequestDto memberSaveRequestDto) {
        Member member = new Member(memberSaveRequestDto);
        if (memberRepository.existsByMemberEmail(member.getMemberEmail())) {
            log.info("이미 존재하는 아이디={}", member.getMemberEmail());
            return memberSaveRequestDto;
        }
        memberRepository.save(member);
        return memberSaveRequestDto;
    }

    @Transactional
    @Override
    public void update(Long memberId, MemberUpdateRequestDto updateParam) {
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
    public MemberResponseDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        MemberResponseDto dto = new MemberResponseDto();
        dto.setMemberPassword(member.getMemberPassword());
        dto.setMemberNickname(member.getMemberNickname());
        dto.setIntroduce(member.getMemberIntroduce());
        return dto;
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
