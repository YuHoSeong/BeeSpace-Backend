package com.creavispace.project.domain.comment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;
import com.creavispace.project.domain.comment.dto.request.ProjectCommentModifyRequestDto;
import com.creavispace.project.domain.comment.dto.response.CommentResponseDto;
import com.creavispace.project.domain.comment.dto.response.CommentDeleteResponseDto;
import com.creavispace.project.domain.comment.service.ProjectCommentService;
import com.creavispace.project.domain.comment.service.RecruitCommentService;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    
    private final ProjectCommentService projectCommentService;
    private final RecruitCommentService recruitCommentService;

    private static final String READ_PROJECT_COMMENT_LIST = "/project";
    private static final String CREATE_PROJECT_COMMENT = "/project";
    private static final String MODIFY_PROJECT_COMMENT = "/project/{projectCommentId}";
    private static final String DELETE_PROJECT_COMMENT = "/project/{projectCommentId}";
    private static final String READ_RECRUIT_COMMENT_LIST = "/recruit";

    @GetMapping(READ_PROJECT_COMMENT_LIST)
    @Operation(summary = "프로젝트 댓글 리스트 조회")
    public ResponseEntity<SuccessResponseDto<List<CommentResponseDto>>> readProjectCommentList(@RequestParam("projectId") Long projectId){
        return ResponseEntity.ok().body(projectCommentService.readProjectCommentList(projectId));
    }

    @PostMapping(CREATE_PROJECT_COMMENT)
    @Operation(summary = "프로젝트 댓글 등록")
    public ResponseEntity<SuccessResponseDto<CommentResponseDto>> createProjectComment(@RequestBody ProjectCommentCreateRequestDto requestBody) {
        return ResponseEntity.ok().body(projectCommentService.createProjectComment(requestBody));
    }

    @PutMapping(MODIFY_PROJECT_COMMENT)
    @Operation(summary = "프로젝트 댓글 수정")
    public ResponseEntity<SuccessResponseDto<CommentResponseDto>> modifyProjectComment(@PathVariable("projectCommentId") Long projectCommentId, @RequestBody ProjectCommentModifyRequestDto requestBody) {
        return ResponseEntity.ok().body(projectCommentService.modifyProjectComment(projectCommentId, requestBody));
    }

    @DeleteMapping(DELETE_PROJECT_COMMENT)
    @Operation(summary = "프로젝트 댓글 삭제")
    public ResponseEntity<SuccessResponseDto<CommentDeleteResponseDto>> deleteProjectComment(@PathVariable("projectCommentId") Long projectCommentId){
        return ResponseEntity.ok().body(projectCommentService.deleteProjectComment(projectCommentId));
    }

    @GetMapping(READ_RECRUIT_COMMENT_LIST)
    @Operation(summary = "모집 댓글 리스트 조회")
    public ResponseEntity<SuccessResponseDto<List<CommentResponseDto>>> readRecruitCommentList(@RequestParam("recruitId") Long recruitId){
        return ResponseEntity.ok().body(recruitCommentService.readRecruitCommentList(recruitId));
    }
    
}
