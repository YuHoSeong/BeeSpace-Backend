package com.creavispace.project.domain.comment.service;

import org.springframework.http.ResponseEntity;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;

public interface ProjectCommentService {
    public ResponseEntity createProjectComment(ProjectCommentCreateRequestDto dto);
}
