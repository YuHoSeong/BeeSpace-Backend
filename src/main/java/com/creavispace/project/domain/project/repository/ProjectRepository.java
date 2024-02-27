package com.creavispace.project.domain.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.project.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    public List<Project> findTop6ByStatusTrueOrderByWeekViewCountDesc();
    public Page<Project> findAllByStatusTrue(Pageable pageable);
    public Optional<Project> findByIdAndStatusTrue(Long projectId);
    public Boolean existsByIdAndMemberId(Long projectId, Long memberId);
    
}
