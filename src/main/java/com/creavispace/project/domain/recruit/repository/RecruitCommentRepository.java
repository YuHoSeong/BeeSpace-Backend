package com.creavispace.project.domain.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitCommentRepository<RecruitComment> extends JpaRepository<RecruitComment, Long> {
}
