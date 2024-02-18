package com.creavispace.project.domain.like.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.ProjectLikeResponseDto;
import com.creavispace.project.domain.like.service.ProjectLikeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/like")
public class ProjectLikeController {
    
    private final ProjectLikeService projectLikeService;

    private static final String PROJECT_LIKE = "/{projectId}";

    @GetMapping(PROJECT_LIKE)
    public ResponseEntity<SuccessResponseDto<ProjectLikeResponseDto>> projectLike(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(projectLikeService.projectLike(projectId));
    }
    
}
