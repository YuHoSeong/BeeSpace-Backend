package com.creavispace.project.domain.bookmark.service;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.bookmark.dto.response.CommunityBookmarkReadResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.CommunityBookmarkResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

@Service
public class CommunityBookmarkServiceImpl implements CommunityBookmarkService{

    @Override
    public SuccessResponseDto<CommunityBookmarkResponseDto> communityBookmark(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'communityBookmark'");
    }

    @Override
    public SuccessResponseDto<CommunityBookmarkReadResponseDto> readCommunityBookmark(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readCommunityBookmark'");
    }
    
}
