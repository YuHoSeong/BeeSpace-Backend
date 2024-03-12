package com.creavispace.project.domain.bookmark.service;

import com.creavispace.project.domain.bookmark.dto.response.BookmarkResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

public interface BookmarkService {
    public SuccessResponseDto<BookmarkResponseDto> bookmarkToggle(Long memberId, Long postId, String type);
    public SuccessResponseDto<BookmarkResponseDto> readBookmark(Long memberId, Long postId, String type);
}
