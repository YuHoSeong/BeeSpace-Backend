package com.creavispace.project.domain.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creavispace.project.domain.project.entity.ProjectTechStack;

public interface ProjectTechStackRepository extends JpaRepository<ProjectTechStack, Long> {
    
}
