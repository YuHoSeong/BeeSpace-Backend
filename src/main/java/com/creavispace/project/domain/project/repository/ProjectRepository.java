package com.creavispace.project.domain.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.search.entity.SearchResult;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    public List<Project> findTop6ByStatusTrueOrderByWeekViewCountDesc();
    public Page<Project> findAllByStatusTrue(Pageable pageable);
    public Page<Project> findAllByStatusTrueAndCategory(String category, Pageable pageable);
    public Optional<Project> findByIdAndStatusTrue(Long projectId);
    public Boolean existsByIdAndMemberId(Long projectId, Long memberId);

    @Query(value = "SELECT 'project' AS postType, p.id AS postId FROM Project p WHERE (p.content LIKE %:text% OR p.title LIKE %:text%) AND p.status = true ORDER BY created_date DESC", nativeQuery = true)
    Page<SearchResult> findProjectSearchData(String text, Pageable pageable);

    @Query(value = "SELECT 'project' AS postType, p.id AS postId FROM Project p WHERE (p.content LIKE %:text% OR p.title LIKE %:text%) AND p.status = true " +
           "UNION " +
           "SELECT 'recruit' AS postType, r.id AS postId FROM Recruit r WHERE (r.content LIKE %:text% OR r.title LIKE %:text%) AND r.status = true " +
           "UNION " +
           "SELECT 'community' AS postType, c.id AS postId FROM Community c WHERE (c.content LIKE %:text% OR c.title LIKE %:text%) AND c.status = true " +
           "ORDER BY created_date DESC ",nativeQuery = true)
    Page<SearchResult> findIntegratedSearchData(String text, Pageable pageable);
    
}
