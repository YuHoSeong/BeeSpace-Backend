package com.creavispace.project.domain.like.service;

import org.springframework.http.ResponseEntity;

public interface ProjectLikeService {
    public ResponseEntity<?> projectLike(Long projectId);
}
