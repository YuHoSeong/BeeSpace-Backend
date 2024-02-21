package com.creavispace.project.domain.comment.service;

import java.util.List;

import com.creavispace.project.domain.comment.dto.response.CommentResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

public interface CommunityCommentService {
    public SuccessResponseDto<List<CommentResponseDto>> readCommunityCommentList(Long communityId);
}
