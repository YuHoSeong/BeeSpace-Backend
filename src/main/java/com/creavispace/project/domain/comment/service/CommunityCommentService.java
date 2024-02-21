package com.creavispace.project.domain.comment.service;

import java.util.List;

import com.creavispace.project.domain.comment.dto.request.CommentRequestDto;
import com.creavispace.project.domain.comment.dto.response.CommentResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

public interface CommunityCommentService {
    public SuccessResponseDto<List<CommentResponseDto>> readCommunityCommentList(Long communityId);
    public SuccessResponseDto<CommentResponseDto> createCommunityComment(Long communityId, CommentRequestDto dto);
    public SuccessResponseDto<CommentResponseDto> modifyCommunityComment(Long communityCommentId, CommentRequestDto dto);
}
