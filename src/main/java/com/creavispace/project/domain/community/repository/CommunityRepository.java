package com.creavispace.project.domain.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.community.entity.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    
}
