package com.creavispace.project.domain.search.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.PostType;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityResponseDto;
import com.creavispace.project.domain.community.repository.CommunityRepository;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.project.repository.ProjectRepository;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.repository.RecruitRepository;
import com.creavispace.project.domain.search.dto.response.SearchListReadResponseDto;
import com.creavispace.project.domain.search.entity.SearchResult;
import com.creavispace.project.global.exception.CreaviCodeException;
import com.creavispace.project.global.exception.GlobalErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{

    private final ProjectRepository projectRepository;
    private final CommunityRepository communityRepository;
    private final RecruitRepository recruitRepository;

    @Override
    public SuccessResponseDto<List<SearchListReadResponseDto>> readSearchList(Integer size, Integer page, String text,
            String type) {
        Pageable pageRequest = PageRequest.of(page-1, size);
        Page<SearchResult> pageable;
        if(type != null && type.isEmpty()){
            if(type.equals(PostType.COMMUNITY.getName())){
                pageable = communityRepository.findCommunitySearchData(text, pageRequest);
            }else if(type.equals(PostType.PROJECT.getName())){
                pageable = projectRepository.findProjectSearchData(text, pageRequest);
            }else if(type.equals(PostType.RECRUIT.getName())){
                pageable = recruitRepository.findRecruitSearchData(text, pageRequest);
            }else{
                throw new CreaviCodeException(GlobalErrorCode.TYPE_NOT_FOUND);
            }
        }else{
            pageable = projectRepository.findIntegratedSearchData(text, pageRequest);
        }

        if(!pageable.hasContent()) throw new CreaviCodeException(GlobalErrorCode.NOT_SEARCH_CONTENT);
        List<SearchResult> searchResults = pageable.getContent();

        List<SearchListReadResponseDto> searchs = searchResults.stream()
            .map(search -> SearchListReadResponseDto.builder()
                .postId(search.getPostId())
                .postType(search.getPostType())
                .build())
            .collect(Collectors.toList());

        return new SuccessResponseDto<>(true, "통합 검색 리스트 조회가 완료되었습니다.", searchs);
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
    public SuccessResponseDto<CommunityResponseDto> readSearchCommunity(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSearchCommunity'");
    }
    
}
