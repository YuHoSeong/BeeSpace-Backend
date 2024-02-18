package com.creavispace.project.domain.like.service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.ProjectLikeResponseDto;

public interface ProjectLikeService {
    public SuccessResponseDto<ProjectLikeResponseDto> projectLike(Long projectId);
}
