package com.creavispace.project.domain.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.community.entity.CommunityHashTag;

@Repository
public interface CommunityHashTagRepository extends JpaRepository<CommunityHashTag, Long>{
    public List<CommunityHashTag> findByCommunityId(Long communityId);
}
