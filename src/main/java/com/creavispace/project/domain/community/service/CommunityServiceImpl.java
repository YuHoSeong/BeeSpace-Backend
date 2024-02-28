package com.creavispace.project.domain.community.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.dto.request.CommunityRequestDto;
import com.creavispace.project.domain.community.dto.response.CommunityResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityDeleteResponseDto;

@Service
public class CommunityServiceImpl implements CommunityService{

    @Override
    public SuccessResponseDto<CommunityResponseDto> createCommunity(CommunityRequestDto requestBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCommunity'");
    }

    @Override
    public SuccessResponseDto<CommunityResponseDto> modifyCommunity(Long communityId,
        CommunityRequestDto requestBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyCommunity'");
    }

    @Override
    public SuccessResponseDto<CommunityDeleteResponseDto> deleteCommunity(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCommunity'");
    }

    @Override
    public SuccessResponseDto<CommunityResponseDto> readCommunity(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readCommunity'");
    }

    @Override
    public SuccessResponseDto<List<CommunityResponseDto>> readCommunityList(String hashTag, String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readCommunityList'");
    }
    
}
