package com.creavispace.project.domain.search.service;

import java.util.List;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityListReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.search.dto.response.SearchListReadResponseDto;

public interface SearchService {
    public SuccessResponseDto<List<SearchListReadResponseDto>> readSearchList(Integer size, Integer page, String text, String type);
    public SuccessResponseDto<ProjectListReadResponseDto> readSearchProject(Long projectId);
    public SuccessResponseDto<RecruitListReadResponseDto> readSearchRecruit(Long recruitId);
    public SuccessResponseDto<CommunityListReadResponseDto> readSearchCommunity(Long communityId);
}
