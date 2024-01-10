package com.creavispace.project.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.creavispace.project.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    @Test
    void testSave() {
        Member member = new Member("123@naver.com", "12345", "김규영", "rlarbdud");
        repository.save(member);
        assertThat(member).isEqualTo(repository.findById(member.getId()).orElseThrow());
    }

    @Test
    void testRead() {
        Member member = repository.findById(1L).orElseThrow();
        System.out.println(member.getMemberEmail());
    }

    @Test
    void testEdit() {
        Member beforeEdit = repository.findById(1L).orElseThrow();
        System.out.println(beforeEdit);
        MemberUpdateDto dto = new MemberUpdateDto("바꿀 비밀번호", "바꿀 닉네임", "바꿀 소개글");
        beforeEdit.setMemberPassword(dto.getMemberPassword());
        beforeEdit.setMemberNickname(dto.getMemberNickname());
        beforeEdit.setMemberIntroduce(dto.getIntroduce());
        repository.save(beforeEdit);
        System.out.println(repository.findById(1L));
    }
}