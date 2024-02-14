package com.creavispace.project.domain.comment.service;

import org.springframework.http.ResponseEntity;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;
import com.creavispace.project.domain.comment.dto.request.ProjectCommentModifyRequestDto;

public interface ProjectCommentService {
    public ResponseEntity<?> createProjectComment(ProjectCommentCreateRequestDto dto);
    public ResponseEntity<?> modifyProjectComment(ProjectCommentModifyRequestDto dto);
    public ResponseEntity<?> deleteProjectComment(Long projectCommentId);
}
