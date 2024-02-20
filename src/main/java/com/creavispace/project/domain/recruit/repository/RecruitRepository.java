package com.creavispace.project.domain.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.recruit.entity.Recruit;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {
}
