package com.creavispace.project.domain.community.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.dto.request.CommunityCreateRequestDto;
import com.creavispace.project.domain.community.dto.request.CommunityModifyRequestDto;
import com.creavispace.project.domain.community.dto.response.CommunityCreateResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityDeleteResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityModifyResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityReadListResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityReadResponseDto;

@Service
public class CommunityServiceImpl implements CommunityService{

    @Override
    public SuccessResponseDto<CommunityCreateResponseDto> createCommunity(CommunityCreateRequestDto requestBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCommunity'");
    }

    @Override
    public SuccessResponseDto<CommunityModifyResponseDto> modifyCommunity(Long communityId,
            CommunityModifyRequestDto requestBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyCommunity'");
    }

    @Override
    public SuccessResponseDto<CommunityDeleteResponseDto> deleteCommunity(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCommunity'");
    }

    @Override
    public SuccessResponseDto<CommunityReadResponseDto> readCommunity(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readCommunity'");
    }

    @Override
    public SuccessResponseDto<List<CommunityReadListResponseDto>> readCommunity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readCommunity'");
    }
    
}
