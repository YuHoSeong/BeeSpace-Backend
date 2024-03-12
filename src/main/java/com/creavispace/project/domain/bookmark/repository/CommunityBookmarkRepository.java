package com.creavispace.project.domain.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.bookmark.entity.CommunityBookmark;

@Repository
public interface CommunityBookmarkRepository extends JpaRepository<CommunityBookmark, Long>{
    public CommunityBookmark findByCommunityIdAndMemberId(Long communityId, Long memberId);
    
}
