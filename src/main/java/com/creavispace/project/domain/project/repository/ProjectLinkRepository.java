package com.creavispace.project.domain.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.project.entity.ProjectLink;

@Repository
public interface ProjectLinkRepository extends JpaRepository<ProjectLink, Long>{
    public void deleteByProjectId(Long projectId);
}
