package com.creavispace.project.domain.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.creavispace.project.domain.project.entity.ProjectLink;

public interface ProjectLinkRepository extends JpaRepository<ProjectLink, Long>{
    
    @Query(value = "DELETE FROM project_link WHERE project_id = :projectId AND id NOT IN :list", nativeQuery = true)
    public void deleteByNotModifyLinkIdList(@Param("projectId") Long projectId, @Param("list") List<Long> modifyLinkIdList);

}
