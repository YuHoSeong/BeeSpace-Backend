package com.creavispace.project.domain.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creavispace.project.domain.comment.entity.ProjectComment;

@Repository
public interface ProjectCommentRepository extends JpaRepository<ProjectComment, Long> {
    public List<ProjectComment> findByProjectId(Long projectId);
}
