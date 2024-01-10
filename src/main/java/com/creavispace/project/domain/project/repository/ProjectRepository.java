package com.creavispace.project.domain.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creavispace.project.domain.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    
}
