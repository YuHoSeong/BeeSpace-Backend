package com.creavispace.project.position.service;

import com.creavispace.project.member.Member;
import com.creavispace.project.position.Position;
import com.creavispace.project.position.repository.PositionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService{
    private final PositionRepository positionRepository;

    @Transactional
    @Override
    public Position save(Position position) {
        positionRepository.save(position);
        return position;
    }

    @Override
    public List<Position> findByMemberId(Member member) {
        return positionRepository.findByMemberId(member);
    }
}
