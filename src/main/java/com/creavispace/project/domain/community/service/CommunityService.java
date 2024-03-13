package com.creavispace.project.domain.community.service;

import java.util.List;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.dto.request.CommunityRequestDto;
import com.creavispace.project.domain.community.dto.response.CommunityResponseDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.creavispace.project.domain.community.dto.response.CommunityDeleteResponseDto;

public interface CommunityService {
    public SuccessResponseDto<CommunityResponseDto> createCommunity(Long memberId, CommunityRequestDto requestBody);
    public SuccessResponseDto<CommunityResponseDto> modifyCommunity(Long memberId, Long communityId, CommunityRequestDto requestBody);
    public SuccessResponseDto<CommunityDeleteResponseDto> deleteCommunity(Long memberId, Long communityId);
    public SuccessResponseDto<CommunityResponseDto> readCommunity(Long memberId, Long communityId, HttpServletRequest request, HttpServletResponse response);
    public SuccessResponseDto<List<CommunityResponseDto>> readCommunityList(Integer size, Integer page, String category, String hashTag, String orderby);
}
