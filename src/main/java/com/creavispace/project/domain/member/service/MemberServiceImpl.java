package com.creavispace.project.domain.member.service;

import com.creavispace.project.config.auth.utils.JwtUtil;
import com.creavispace.project.domain.member.dto.response.MemberResponseDto;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.domain.mypage.dto.request.MyPageModifyRequestDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Transactional
    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public void update(Member member, MyPageModifyRequestDto updateParam) {
        member.setMemberNickname(updateParam.nickName());
        member.setMemberIntroduce(updateParam.introduce());
        member.setMemberPosition(updateParam.position());
        member.setMemberCareer(updateParam.career());
        member.setInterestedStack(updateParam.interestedStack());
        member.setProfileUrl(updateParam.profileUrl());
        memberRepository.save(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByMemberEmail(email);
    }

    @Override
    public MemberResponseDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        MemberResponseDto dto = new MemberResponseDto(member);
        return dto;
    }

    @Override
    public Optional<Member> findByEmailAndNameAndLoginId(String email, String name, String loginId) {
        return memberRepository.findByMemberEmailAndMemberNameAndLoginId(email, name, loginId);
    }

    @Override
    public boolean emailExsists(String memberEmail) {
        return memberRepository.existsByMemberEmail(memberEmail);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public String login(String memberEmail, String loginType, Long memberId) {

        return JwtUtil.createJwt(memberEmail, loginType, memberId, jwtSecret, 1000 * 60 * 60L);
    }

    @Override
    public Optional<Member> findByEmailAndLoginTypeAndMemberId(String memberEmail, String loginType, Long memberId) {
        return memberRepository.findByMemberEmailAndLoginTypeAndId(memberEmail, loginType, memberId);
    }

    @Override
    public List<MemberResponseDto> findByMemberNicknameOrIdTagContaining(String search) {
        List<Member> searchData = memberRepository.findByMemberNicknameOrIdTagContaining(search);
        return searchData.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }
    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
