package com.creavispace.project.domain.bookmark.service;

import com.creavispace.project.domain.bookmark.dto.response.BookmarkContentsResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.BookmarkResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BookmarkService {
    public SuccessResponseDto<BookmarkResponseDto> bookmarkToggle(Long memberId, Long postId, String postType);
    public SuccessResponseDto<BookmarkResponseDto> readBookmark(Long memberId, Long postId, String postType);

    //ky
    public SuccessResponseDto<BookmarkContentsResponseDto> readMyBookmark(Long memberId, String postType)
            throws JsonProcessingException;
}
