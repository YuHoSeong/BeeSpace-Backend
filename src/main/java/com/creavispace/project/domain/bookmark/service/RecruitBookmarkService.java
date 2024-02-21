package com.creavispace.project.domain.bookmark.service;

import com.creavispace.project.domain.bookmark.dto.response.RecruitBookmarkReadResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.RecruitBookmarkResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

public interface RecruitBookmarkService {
    public SuccessResponseDto<RecruitBookmarkResponseDto> recruitBookmark(Long recruitId);
    public SuccessResponseDto<RecruitBookmarkReadResponseDto> readRcruitBookmark(Long recruitId);
}
