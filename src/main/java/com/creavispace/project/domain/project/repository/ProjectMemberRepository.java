package com.creavispace.project.domain.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.project.entity.ProjectMember;

import jakarta.transaction.Transactional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long>{
    public void deleteByProjectId(Long projectId);
    public List<ProjectMember> findByProjectId(Long projectId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM project_member WHERE id NOT IN :idList",nativeQuery = true)
    public void deleteByNotModifyMemberIdList(@Param("idList") List<Long> modifyMemberIdList);
}
