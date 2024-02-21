package com.creavispace.project.domain.comment.service;

import java.util.List;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;
import com.creavispace.project.domain.comment.dto.request.ProjectCommentModifyRequestDto;
import com.creavispace.project.domain.comment.dto.response.CommentResponseDto;
import com.creavispace.project.domain.comment.dto.response.CommentDeleteResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

public interface ProjectCommentService {
    public SuccessResponseDto<List<CommentResponseDto>> readProjectCommentList(Long projectId);
    public SuccessResponseDto<CommentResponseDto> createProjectComment(ProjectCommentCreateRequestDto dto);
    public SuccessResponseDto<CommentResponseDto> modifyProjectComment(Long projectCommentId, ProjectCommentModifyRequestDto dto);
    public SuccessResponseDto<CommentDeleteResponseDto> deleteProjectComment(Long projectCommentId);
}
