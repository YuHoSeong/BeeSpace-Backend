package com.creavispace.project.domain.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.common.entity.TechStack;

@Repository
public interface TechStackRepository extends JpaRepository<TechStack, Long>{
    
}
