package com.creavispace.project.domain.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creavispace.project.domain.project.entity.ProjectLink;

public interface ProjectLinkRepository extends JpaRepository<ProjectLink, Long>{
    
}
