package com.creavispace.project.domain.like.service;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.LikeResponseDto;

@Service
public class CommunityLikeServiceImpl implements CommunityLikeService {

    @Override
    public SuccessResponseDto<LikeResponseDto> communityLike(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'communityLike'");
    }

    @Override
    public SuccessResponseDto<LikeResponseDto> readCommunityLike(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readCommunityLike'");
    }
    
}
