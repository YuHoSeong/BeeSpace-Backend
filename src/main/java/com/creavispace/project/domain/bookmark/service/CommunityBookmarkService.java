package com.creavispace.project.domain.bookmark.service;

import com.creavispace.project.domain.bookmark.dto.response.CommunityBookmarkReadResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.CommunityBookmarkResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

public interface CommunityBookmarkService {
    public SuccessResponseDto<CommunityBookmarkResponseDto> communityBookmark(Long communityId);
    public SuccessResponseDto<CommunityBookmarkReadResponseDto> readCommunityBookmark(Long communityId);
}
