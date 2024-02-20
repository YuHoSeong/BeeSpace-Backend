package com.creavispace.project.domain.like.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.ProjectLikeResponseDto;
import com.creavispace.project.domain.like.service.ProjectLikeService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class ProjectLikeController {
    
    private final ProjectLikeService projectLikeService;

    private static final String PROJECT_LIKE = "";

    @GetMapping(PROJECT_LIKE)
    @Operation(summary = "프로젝트 좋아요 토글 기능")
    public ResponseEntity<SuccessResponseDto<ProjectLikeResponseDto>> projectLike(@RequestParam("projectId") Long projectId) {
        return ResponseEntity.ok().body(projectLikeService.projectLike(projectId));
    }
    
}
