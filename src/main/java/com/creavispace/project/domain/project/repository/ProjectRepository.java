package com.creavispace.project.domain.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.creavispace.project.domain.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    public List<Project> findTop5ByOrderByWeekViewCountDesc();
    public Page<Project> findAllOrderByCreatedDateDesc(Pageable pageable);
    
}
