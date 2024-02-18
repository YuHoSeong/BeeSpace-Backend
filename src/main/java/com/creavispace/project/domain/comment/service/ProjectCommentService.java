package com.creavispace.project.domain.comment.service;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;
import com.creavispace.project.domain.comment.dto.request.ProjectCommentModifyRequestDto;
import com.creavispace.project.domain.comment.dto.response.ProjectCommentCreateResponseDto;
import com.creavispace.project.domain.comment.dto.response.ProjectCommentModifyResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

public interface ProjectCommentService {
    public SuccessResponseDto<ProjectCommentCreateResponseDto> createProjectComment(ProjectCommentCreateRequestDto dto);
    public SuccessResponseDto<ProjectCommentModifyResponseDto> modifyProjectComment(ProjectCommentModifyRequestDto dto);
    public SuccessResponseDto<Long> deleteProjectComment(Long projectCommentId);
}
