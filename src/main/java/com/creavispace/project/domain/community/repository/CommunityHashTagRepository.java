package com.creavispace.project.domain.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.community.entity.CommunityHashTag;

import jakarta.transaction.Transactional;

@Repository
public interface CommunityHashTagRepository extends JpaRepository<CommunityHashTag, Long>{
    public List<CommunityHashTag> findByCommunityId(Long communityId);
    @Transactional
    public void deleteByCommunityId(Long communityId);
    @Query(value = "SELECT ch FROM CommunityHashTag ch GROUP BY ch.hashTagId ORDER BY COUNT(ch.hashTagId) DESC LIMIT 10", nativeQuery = true)
    public List<CommunityHashTag> findTop10HashTag();
}
