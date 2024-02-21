package com.creavispace.project.domain.bookmark.service;

import com.creavispace.project.domain.bookmark.dto.response.ProjectBookmarkReadResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.ProjectBookmarkResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

public interface ProjectBookmarkService {
    public SuccessResponseDto<ProjectBookmarkResponseDto> projectBookmark(Long projectId);
    public SuccessResponseDto<ProjectBookmarkReadResponseDto> readProjectBookmark(Long projectId);
}
