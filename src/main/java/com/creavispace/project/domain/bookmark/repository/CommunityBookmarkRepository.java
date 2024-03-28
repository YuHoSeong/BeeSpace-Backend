package com.creavispace.project.domain.bookmark.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.bookmark.entity.CommunityBookmark;

@Repository
public interface CommunityBookmarkRepository extends JpaRepository<CommunityBookmark, Long>{
    public CommunityBookmark findByCommunityIdAndMemberId(Long communityId, Long memberId);

    List<CommunityBookmark> findByMemberId(Long memberId);
}
