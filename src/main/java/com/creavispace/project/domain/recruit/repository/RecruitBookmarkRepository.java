package com.creavispace.project.domain.recruit.repository;

import com.creavispace.project.domain.recruit.entity.RecruitBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitBookmarkRepository extends JpaRepository<RecruitBookmark, Long> {
}
