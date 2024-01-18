package com.creavispace.project.domain.comment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;
import com.creavispace.project.domain.comment.dto.request.ProjectCommentModifyRequestDto;
import com.creavispace.project.domain.comment.service.ProjectCommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/project/comment")
public class ProjectCommentController {
    
    private final ProjectCommentService projectCommentService;

    private static final String CREATE_PROJECT_COMMENT = "/";
    private static final String MODIFY_PROJECT_COMMENT = "/";
    private static final String DELETE_PROJECT_COMMENT = "/{projectCommentId}";

    @PostMapping(CREATE_PROJECT_COMMENT)
    public ResponseEntity createProjectComment(@RequestBody ProjectCommentCreateRequestDto requestBody) {
        return projectCommentService.createProjectComment(requestBody);
    }

    @PutMapping(MODIFY_PROJECT_COMMENT)
    public ResponseEntity modifyProjectComment(@RequestBody ProjectCommentModifyRequestDto requestBody) {
        return projectCommentService.modifyProjectComment(requestBody);
    }

    @DeleteMapping(DELETE_PROJECT_COMMENT)
    public ResponseEntity deleteProjectComment(@PathVariable("projectCommentId") long projectCommentId){
        return projectCommentService.deleteProjectComment(projectCommentId);
    }
    
}