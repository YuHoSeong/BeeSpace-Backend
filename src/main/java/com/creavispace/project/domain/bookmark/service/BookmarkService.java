package com.creavispace.project.domain.bookmark.service;

import com.creavispace.project.domain.bookmark.dto.response.BookmarkContentsResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.BookmarkResponseDto;
import com.creavispace.project.domain.common.dto.response.SuccessResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.creavispace.project.domain.common.dto.type.PostType;

public interface BookmarkService {
    public SuccessResponseDto<BookmarkResponseDto> bookmarkToggle(Long memberId, Long postId, PostType postType);
    public SuccessResponseDto<BookmarkResponseDto> readBookmark(Long memberId, Long postId, PostType postType);


    //ky
    public SuccessResponseDto<BookmarkContentsResponseDto> readMyBookmark(Long memberId, String postType)
            throws JsonProcessingException;
}
