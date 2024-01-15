package com.creavispace.project.domain.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creavispace.project.domain.like.entity.ProjectLike;

public interface ProjectLikeRepository extends JpaRepository<ProjectLike, Long> {
    public Boolean existsByProjectIdAndMemberId();
}
