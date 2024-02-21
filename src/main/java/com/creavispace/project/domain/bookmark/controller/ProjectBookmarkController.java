package com.creavispace.project.domain.bookmark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.bookmark.dto.response.ProjectBookmarkResponseDto;
import com.creavispace.project.domain.bookmark.service.ProjectBookmarkService;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class ProjectBookmarkController {
    
    private final ProjectBookmarkService projectBookmarkService;

    private static final String TOGGLE_PROJECT_BOOKMARK = "/project/{projectId}";

    @PostMapping(TOGGLE_PROJECT_BOOKMARK)
    @Operation(summary = "프로젝트 북마크 토글 기능")
    public ResponseEntity<SuccessResponseDto<ProjectBookmarkResponseDto>> projectBookmark(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(projectBookmarkService.projectBookmark(projectId));
    }
    
}
