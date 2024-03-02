package com.creavispace.project.domain.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.community.entity.CommunityHashTag;

@Repository
public interface CommunityHashTagRepository extends JpaRepository<CommunityHashTag, Long>{
    public List<CommunityHashTag> findByCommunityId(Long communityId);
    public void deleteByCommunityId(Long communityId);
    @Query(value = "SELECT ch FROM CommunityHashTag ch GROUP BY ch.hashTagId ORDER BY COUNT(ch.hashTagId) DESC LIMIT 10")
    public List<CommunityHashTag> findTop10HashTag();
}
