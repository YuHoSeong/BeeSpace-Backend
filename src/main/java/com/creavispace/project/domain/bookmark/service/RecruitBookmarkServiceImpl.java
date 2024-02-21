package com.creavispace.project.domain.bookmark.service;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.bookmark.dto.response.RecruitBookmarkReadResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.RecruitBookmarkResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

@Service
public class RecruitBookmarkServiceImpl implements RecruitBookmarkService{

    @Override
    public SuccessResponseDto<RecruitBookmarkResponseDto> recruitBookmark(Long recruitId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recruitBookmark'");
    }

    @Override
    public SuccessResponseDto<RecruitBookmarkReadResponseDto> readRcruitBookmark(Long recruitId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readRcruitBookmark'");
    }
    
}
