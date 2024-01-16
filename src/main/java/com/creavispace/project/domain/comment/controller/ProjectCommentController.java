package com.creavispace.project.domain.comment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;
import com.creavispace.project.domain.comment.service.ProjectCommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/project/comment")
public class ProjectCommentController {
    
    private final ProjectCommentService projectCommentService;

    private static final String CREATE_PROJECT_COMMENT = "/";

    @PostMapping(CREATE_PROJECT_COMMENT)
    public ResponseEntity createProjectComment(@RequestBody ProjectCommentCreateRequestDto requestBody) {
        return projectCommentService.createProjectComment(requestBody);
    }
    
}
