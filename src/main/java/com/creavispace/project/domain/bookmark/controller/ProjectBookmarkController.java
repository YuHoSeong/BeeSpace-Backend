package com.creavispace.project.domain.bookmark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.bookmark.dto.response.ProjectBookmarkResponseDto;
import com.creavispace.project.domain.bookmark.service.ProjectBookmarkService;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@RequestMapping("/project/bookmark")
public class ProjectBookmarkController {
    
    private final ProjectBookmarkService projectBookmarkService;

    private static final String PROJECT_BOOKMARK = "/{projectId}";

    @GetMapping(PROJECT_BOOKMARK)
    public ResponseEntity<SuccessResponseDto<ProjectBookmarkResponseDto>> projectBookmark(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(projectBookmarkService.projectBookmark(projectId));
    }
    
}
