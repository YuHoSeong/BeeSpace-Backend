package com.creavispace.project.domain.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitPositionRepository<RecruitPosition> extends JpaRepository<RecruitPosition, Long> {
}
