package com.creavispace.project.domain.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.like.entity.ProjectLike;

@Repository
public interface ProjectLikeRepository extends JpaRepository<ProjectLike, Long> {
    public Boolean existsByProjectIdAndMemberId(Long projectId, Long memberId);
}
