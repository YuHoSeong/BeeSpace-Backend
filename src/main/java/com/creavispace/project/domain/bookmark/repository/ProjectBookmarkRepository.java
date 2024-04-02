package com.creavispace.project.domain.bookmark.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.bookmark.entity.ProjectBookmark;

@Repository
public interface ProjectBookmarkRepository extends JpaRepository<ProjectBookmark, Long>{
    public Boolean existsByProjectIdAndMemberId(Long projectId, Long memberId);
    public ProjectBookmark findByProjectIdAndMemberId(Long projectId, Long memberId);


    //ky
    List<ProjectBookmark> findByMemberId(Long memberId, Pageable pageable);

    List<ProjectBookmark> findByMemberIdOrderByContentsCreatedDateAsc(Long memberId, Pageable pageRequest);
    List<ProjectBookmark> findByMemberIdOrderByContentsCreatedDateDesc(Long memberId, Pageable pageRequest);
}
