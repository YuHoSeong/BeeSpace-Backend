package com.creavispace.project.domain.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.recruit.entity.RecruitTechStack;

@Repository
public interface RecruitTechStackRepository extends JpaRepository<RecruitTechStack, Long> {
}
