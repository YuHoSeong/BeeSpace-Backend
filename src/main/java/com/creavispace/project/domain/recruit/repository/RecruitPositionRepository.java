package com.creavispace.project.domain.recruit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.recruit.entity.RecruitPosition;

@Repository
public interface RecruitPositionRepository extends JpaRepository<RecruitPosition, Long> {
    public List<RecruitPosition> findByRecruitId(Long recruitId);
}
