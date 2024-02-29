package com.creavispace.project.domain.community.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.community.entity.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    public Optional<Community> findByIdAndStatusTrue(Long communityId);
    public Page<Community> findAllByStatusTrueAndCategoryAndHashTagId(String category, Long hashTagId, Pageable pageable);
    public Page<Community> findAllByStatusTrueAndHashTagId(Long hashTagId, Pageable pageable);
    public Page<Community> findAllByStatusTrueAndCategory(String category, Pageable pageable);
    public Page<Community> findAllByStatusTrue(Pageable pageable);
}
