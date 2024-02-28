package com.creavispace.project.domain.recruit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.recruit.entity.Recruit;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    public Page<Recruit> findAllByStatusTrueAndCategory(String category, Pageable pageRequest);
    public Page<Recruit> findAllByStatusTrue(Pageable pageRequest);
    public Optional<Recruit> findByIdAndStatusTrue(Long recruitId);
    public List<Recruit> findTop3ByStatusTrueOrderByEndDesc();
}
