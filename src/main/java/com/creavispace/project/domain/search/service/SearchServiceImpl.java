package com.creavispace.project.domain.search.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityListReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.search.dto.response.SearchListReadResponseDto;

@Service
public class SearchServiceImpl implements SearchService{

    @Override
    public SuccessResponseDto<List<SearchListReadResponseDto>> readSearchList(Integer size, Integer page, String text,
            String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSearchList'");
    }

    @Override
    public SuccessResponseDto<ProjectListReadResponseDto> readSearchProject(Long projectId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSearchProject'");
    }

    @Override
    public SuccessResponseDto<RecruitListReadResponseDto> readSearchRecruit(Long recruitId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSearchRecruit'");
    }

    @Override
    public SuccessResponseDto<CommunityListReadResponseDto> readSearchCommunity(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSearchCommunity'");
    }
    
}
