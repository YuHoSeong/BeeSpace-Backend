package com.creavispace.project.domain.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.project.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    public List<Project> findTop5ByStatusTrueOrderByWeekViewCountDesc();
    public Page<Project> findAllByStatusTrue(Pageable pageable);
    public Project findByIdAndStatusTrue(Long projectId);
    
}
