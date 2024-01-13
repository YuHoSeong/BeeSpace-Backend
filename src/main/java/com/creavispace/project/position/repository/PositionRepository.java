package com.creavispace.project.position.repository;

import com.creavispace.project.member.Member;
import com.creavispace.project.position.Position;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByMemberId(Member member);
}
