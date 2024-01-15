package com.creavispace.project.domain.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.project.entity.ProjectMember;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long>{
    
    @Query(value = "DELETE FROM project_member WHERE project_id = :projectId AND id NOT IN :list", nativeQuery = true)
    public void deleteByNotModifyMemberIdList(@Param("projectId") Long projectId, @Param("list") List<Long> modifyMemberIdList);
}
