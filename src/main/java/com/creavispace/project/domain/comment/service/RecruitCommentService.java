package com.creavispace.project.domain.comment.service;

import java.util.List;

import com.creavispace.project.domain.comment.dto.request.CommentRequestDto;
import com.creavispace.project.domain.comment.dto.response.CommentResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

public interface RecruitCommentService {
    public SuccessResponseDto<List<CommentResponseDto>> readRecruitCommentList(Long recruitId);
    public SuccessResponseDto<CommentResponseDto> createRecruitComment(Long recruitId, CommentRequestDto dto);
}
