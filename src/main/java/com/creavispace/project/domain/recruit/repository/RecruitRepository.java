package com.creavispace.project.domain.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepository<Recruit> extends JpaRepository<Recruit, Long> {
}
