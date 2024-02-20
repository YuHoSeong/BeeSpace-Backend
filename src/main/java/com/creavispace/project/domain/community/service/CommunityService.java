package com.creavispace.project.domain.community.service;

import java.util.List;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.dto.request.CommunityCreateRequestDto;
import com.creavispace.project.domain.community.dto.request.CommunityModifyRequestDto;
import com.creavispace.project.domain.community.dto.response.CommunityCreateResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityDeleteResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityModifyResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityReadListResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityReadResponseDto;

public interface CommunityService {
    public SuccessResponseDto<CommunityCreateResponseDto> createCommunity(CommunityCreateRequestDto requestBody);
    public SuccessResponseDto<CommunityModifyResponseDto> modifyCommunity(Long communityId, CommunityModifyRequestDto requestBody);
    public SuccessResponseDto<CommunityDeleteResponseDto> deleteCommunity(Long communityId);
    public SuccessResponseDto<CommunityReadResponseDto> readCommunity(Long communityId);
    public SuccessResponseDto<List<CommunityReadListResponseDto>> readCommunityList(String hashTag, String type);
}
