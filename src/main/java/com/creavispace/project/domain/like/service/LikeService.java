package com.creavispace.project.domain.like.service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.LikeCountResponseDto;

public interface LikeService {
    public SuccessResponseDto<LikeCountResponseDto> likeCount(String postType, Long postId);
}
