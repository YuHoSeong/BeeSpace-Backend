package com.creavispace.project.domain.like.service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.LikeResponseDto;

public interface CommunityLikeService {
    public SuccessResponseDto<LikeResponseDto> communityLike(Long memberId, Long communityId);
    public SuccessResponseDto<LikeResponseDto> readCommunityLike(Long memberId, Long communityId);
}
