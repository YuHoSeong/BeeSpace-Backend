package com.creavispace.project.domain.bookmark.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.bookmark.entity.RecruitBookmark;

@Repository
public interface RecruitBookmarkRepository extends JpaRepository<RecruitBookmark, Long> {
    public RecruitBookmark findByRecruitIdAndMemberId(Long recruitId, Long memberId);

    List<RecruitBookmark> findByMemberId(Long memberId, Pageable pageable);

    List<RecruitBookmark> findByMemberIdOrderByContentsCreatedDateDesc(Long memberId, Pageable pageRequest);

    List<RecruitBookmark> findByMemberIdOrderByContentsCreatedDateAsc(Long memberId, Pageable pageRequest);
}
