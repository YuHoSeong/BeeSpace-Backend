package com.creavispace.project.position.service;

import com.creavispace.project.member.Member;
import com.creavispace.project.position.Position;
import com.creavispace.project.position.Positions;
import java.util.List;
import java.util.Optional;

public interface PositionService {
    Position save(Position position);

    List<Position> findByMemberId(Member member);
}
