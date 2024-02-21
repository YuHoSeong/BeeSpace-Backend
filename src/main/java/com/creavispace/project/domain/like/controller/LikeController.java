package com.creavispace.project.domain.like.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.LikeResponseDto;
import com.creavispace.project.domain.like.service.CommunityLikeService;
import com.creavispace.project.domain.like.service.ProjectLikeService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {
    
    private final ProjectLikeService projectLikeService;
    private final CommunityLikeService communityLikeService;

    private static final String PROJECT_LIKE = "/project/{projectId}";
    private static final String READ_PROJECT_LIKE = "/project/{projectId}";
    private static final String COMMUNITY_LIKE = "/community/{communityId}";
    private static final String READ_COMMUNITY_LIKE = "/community/{communityId}";

    @PostMapping(PROJECT_LIKE)
    @Operation(summary = "프로젝트 좋아요 토글 기능")
    public ResponseEntity<SuccessResponseDto<LikeResponseDto>> projectLike(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(projectLikeService.projectLike(projectId));
    }

    @GetMapping(READ_PROJECT_LIKE)
    @Operation(summary = "프로젝트 좋아요 조회 기능")
    public ResponseEntity<SuccessResponseDto<LikeResponseDto>> readProjectLike(@PathVariable("projectId") Long projectId){
        return ResponseEntity.ok().body(projectLikeService.readProjectLike(projectId));
    }

    @PostMapping(COMMUNITY_LIKE)
    @Operation(summary = "커뮤니티 좋아요 토글 기능")
    public ResponseEntity<SuccessResponseDto<LikeResponseDto>> communityLike(@PathVariable("communityId") Long communityId){
        return ResponseEntity.ok().body(communityLikeService.communityLike(communityId));
    }

    @GetMapping(READ_COMMUNITY_LIKE)
    @Operation(summary = "커뮤니티 좋아요 조회 기능")
    public ResponseEntity<SuccessResponseDto<LikeResponseDto>> readCommunityLike(@PathVariable("communityId") Long communityId){
        return ResponseEntity.ok().body(communityLikeService.readCommunityLike(communityId));
    }
    
}
