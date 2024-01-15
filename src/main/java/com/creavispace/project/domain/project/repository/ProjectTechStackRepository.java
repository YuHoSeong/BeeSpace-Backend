package com.creavispace.project.domain.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.project.entity.ProjectTechStack;

@Repository
public interface ProjectTechStackRepository extends JpaRepository<ProjectTechStack, Long> {
    public void deleteByProjectId(Long projectId);
}
