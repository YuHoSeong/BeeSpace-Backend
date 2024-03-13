package com.creavispace.project.domain.recruit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.recruit.entity.Recruit;
import com.creavispace.project.domain.search.entity.SearchResult;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    public Page<Recruit> findAllByStatusTrueAndCategory(String category, Pageable pageRequest);
    public Page<Recruit> findAllByStatusTrue(Pageable pageRequest);
    public Optional<Recruit> findByIdAndStatusTrue(Long recruitId);
    public boolean existsByIdAndMemberId(Long recruitId, Long memberId);
    public List<Recruit> findTop3ByStatusTrueOrderByEndAsc();

    @Query(value = "SELECT 'recruit' AS postType, r.id AS postId FROM Recruit r WHERE (r.content LIKE %:text% OR r.title LIKE %:text%) AND r.status = true ORDER BY created_date DESC", nativeQuery = true)
    public Page<SearchResult> findRecruitSearchData(String text, Pageable pageable);

    @Query(value = "SELECT 'project' AS postType, p.id AS postId FROM Project p WHERE (p.content LIKE %:text% OR p.title LIKE %:text%) AND p.status = true " +
           "UNION " +
           "SELECT 'recruit' AS postType, r.id AS postId FROM Recruit r WHERE (r.content LIKE %:text% OR r.title LIKE %:text%) AND r.status = true " +
           "UNION " +
           "SELECT 'community' AS postType, c.id AS postId FROM Community c WHERE (c.content LIKE %:text% OR c.title LIKE %:text%) AND c.status = true " +
           "ORDER BY created_date DESC ",nativeQuery = true)
    public Page<SearchResult> findIntegratedSearchData(String text, Pageable pageable);
}
