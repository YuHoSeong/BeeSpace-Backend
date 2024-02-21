package com.creavispace.project.domain.like.service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.LikeResponseDto;

public interface ProjectLikeService {
    public SuccessResponseDto<LikeResponseDto> projectLike(Long projectId);
    public SuccessResponseDto<LikeResponseDto> readProjectLike(Long projectId);
}
