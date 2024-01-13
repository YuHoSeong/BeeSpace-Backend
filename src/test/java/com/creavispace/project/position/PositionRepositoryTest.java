package com.creavispace.project.position;

import com.creavispace.project.member.Member;
import com.creavispace.project.member.repository.MemberRepository;
import com.creavispace.project.position.repository.PositionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PositionRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PositionRepository positionRepository;

    @Test
    @Commit
    void positionSave() {
        Position position = new Position();
        position.setPosition(Positions.Backend.position);
        position.setMemberId(memberRepository.findById(1L).orElseThrow());
        positionRepository.save(position);
    }

    @Test
    void positionRead() {
        Member member = memberRepository.findById(1L).orElseThrow();
        Position position = positionRepository.findById(member.getId()).orElseThrow();
        System.out.println(position);
        System.out.println(position.getMemberId());
        System.out.println(positionRepository.findByMemberId(member));

    }
}
