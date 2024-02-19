package com.creavispace.project.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.creavispace.project.domain.member.Role;
import com.creavispace.project.domain.member.dto.request.MemberUpdateRequestDto;
import com.creavispace.project.domain.member.dto.request.MemberSaveRequestDto;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    @Test
    @Commit
    void testSave() {
        MemberSaveRequestDto dto = MemberSaveRequestDto.builder()
                .memberEmail("123@naver.com")
                .memberName("김규영")
                .memberNickname("rlardud1")
                .role(Role.MEMBER)
                .memberNickname("rlardud1")
                .build();

        Member member = new Member(dto);
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
        MemberUpdateRequestDto dto = new MemberUpdateRequestDto();
        dto.setMemberPassword("바뀜");
        dto.setMemberNickname("999999999999");
        beforeEdit.setMemberPassword(dto.getMemberPassword());
        beforeEdit.setMemberNickname(dto.getMemberNickname());
        beforeEdit.setMemberIntroduce(dto.getIntroduce());
        repository.save(beforeEdit);
        System.out.println(repository.findById(1L));
    }
}