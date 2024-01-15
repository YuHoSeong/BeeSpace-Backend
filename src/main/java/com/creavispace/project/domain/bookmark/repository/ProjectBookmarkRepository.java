package com.creavispace.project.domain.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.bookmark.entity.ProjectBookmark;

@Repository
public interface ProjectBookmarkRepository extends JpaRepository<ProjectBookmark, Long>{
    public Boolean existsByProjectIdAndMemberId();
}
