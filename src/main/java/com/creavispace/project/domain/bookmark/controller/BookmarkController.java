package com.creavispace.project.domain.bookmark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.bookmark.dto.response.ProjectBookmarkReadResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.ProjectBookmarkResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.RecruitBookmarkResponseDto;
import com.creavispace.project.domain.bookmark.service.ProjectBookmarkService;
import com.creavispace.project.domain.bookmark.service.RecruitBookmarkService;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {
    
    private final ProjectBookmarkService projectBookmarkService;
    private final RecruitBookmarkService recruitBookmarkService;

    private static final String TOGGLE_PROJECT_BOOKMARK = "/project/{projectId}";
    private static final String READ_PROJECT_BOOKMARK = "/project/{projectId}";
    private static final String TOGGLE_RECRUIT_BOOKMARK = "/recruit/{recruitId}";

    @PostMapping(TOGGLE_PROJECT_BOOKMARK)
    @Operation(summary = "프로젝트 북마크 토글 기능")
    public ResponseEntity<SuccessResponseDto<ProjectBookmarkResponseDto>> projectBookmark(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(projectBookmarkService.projectBookmark(projectId));
    }
    
    @GetMapping(READ_PROJECT_BOOKMARK)
    @Operation(summary = "프로젝트 북마크 조회 기능")
    public ResponseEntity<SuccessResponseDto<ProjectBookmarkReadResponseDto>> readProjectBookmark(@PathVariable("projectId") Long projectId){
        return ResponseEntity.ok().body(projectBookmarkService.readProjectBookmark(projectId));
    }
    
    @PostMapping(TOGGLE_RECRUIT_BOOKMARK)
    @Operation(summary = "모집 게시글 북마크 토글 기능")
    public ResponseEntity<SuccessResponseDto<RecruitBookmarkResponseDto>> recruitBookmark(@PathVariable("recruitId") Long recruitId) {
        return ResponseEntity.ok().body(recruitBookmarkService.recruitBookmark(recruitId));
    }
    
}
