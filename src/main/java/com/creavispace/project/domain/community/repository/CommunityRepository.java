package com.creavispace.project.domain.community.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.community.entity.Community;
import com.creavispace.project.domain.search.entity.SearchResult;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    public Optional<Community> findByIdAndStatusTrue(Long communityId);
    
    @Query(value = "SELECT * " +
    "FROM Community " +
    "WHERE id IN (" +
    "SELECT community_id " +
    "FROM community_hash_tag " +
    "WHERE hash_tag_id = :hashTagId) " +
    "AND category = :category " +
    "ORDER BY created_date DESC ", nativeQuery = true)
    public Page<Community> findAllByStatusTrueAndCategoryAndHashTagId(String category, Long hashTagId, Pageable pageable);

    @Query(value = "SELECT * " +
    "FROM Community " +
    "WHERE id IN (" +
    "SELECT community_id " +
    "FROM community_hash_tag " +
    "WHERE hash_tag_id = :hashTagId) " +
    "ORDER BY created_date DESC ", nativeQuery = true)
    public Page<Community> findAllByStatusTrueAndHashTagId(Long hashTagId, Pageable pageable);

    public Page<Community> findAllByStatusTrueAndCategory(String category, Pageable pageable);

    public Page<Community> findAllByStatusTrue(Pageable pageable);

    @Query(value = "SELECT 'community' AS postType, c.id AS postId FROM Community c WHERE (c.content LIKE %:text% OR c.title LIKE %:text%) AND c.status = true ORDER BY created_date DESC", nativeQuery = true)
    Page<SearchResult> findCommunitySearchData(String text, Pageable pageable);

    @Query(value = "SELECT 'project' AS postType, p.id AS postId FROM Project p WHERE (p.content LIKE %:text% OR p.title LIKE %:text%) AND p.status = true " +
           "UNION " +
           "SELECT 'recruit' AS postType, r.id AS postId FROM Recruit r WHERE (r.content LIKE %:text% OR r.title LIKE %:text%) AND r.status = true " +
           "UNION " +
           "SELECT 'community' AS postType, c.id AS postId FROM Community c WHERE (c.content LIKE %:text% OR c.title LIKE %:text%) AND c.status = true " +
           "ORDER BY created_date DESC ",nativeQuery = true)
    Page<SearchResult> findIntegratedSearchData(String text, Pageable pageable);
}
