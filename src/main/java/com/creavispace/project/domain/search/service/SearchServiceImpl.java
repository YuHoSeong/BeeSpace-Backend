package com.creavispace.project.domain.search.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.PostType;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityHashTagDto;
import com.creavispace.project.domain.community.dto.response.CommunityResponseDto;
import com.creavispace.project.domain.community.entity.Community;
import com.creavispace.project.domain.community.repository.CommunityRepository;
import com.creavispace.project.domain.project.dto.response.ProjectLinkResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.project.repository.ProjectRepository;
import com.creavispace.project.domain.project.service.ProjectService;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitTechStackResponseDto;
import com.creavispace.project.domain.recruit.entity.Recruit;
import com.creavispace.project.domain.recruit.repository.RecruitPositionRepository;
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
    private final RecruitPositionRepository recruitPositionRepository;

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

        Project project = projectRepository.findByIdAndStatusTrue(projectId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND));
        
        ProjectListReadResponseDto projectCard = ProjectListReadResponseDto.builder()
            .id(project.getId())
            .postType(PostType.PROJECT.getName())
            .category(project.getCategory())
            .title(project.getTitle())
            .links(project.getLinks().stream()
                .map(link -> ProjectLinkResponseDto.builder()
                    .type(link.getType())
                    .url(link.getUrl())
                    .build())
                .collect(Collectors.toList()))
            .thumbnail(project.getThumbnail())
            .bannerContent(project.getBannerContent())
            .build();

        return new SuccessResponseDto<>(true, "통합검색 프로젝트 카드 정보 조회가 완료되었습니다.", projectCard);
    }

    @Override
    public SuccessResponseDto<RecruitListReadResponseDto> readSearchRecruit(Long recruitId) {

        Recruit recruit = recruitRepository.findByIdAndStatusTrue(recruitId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.RECRUIT_NOT_FOUND));
        
        RecruitListReadResponseDto recruitCard = RecruitListReadResponseDto.builder()
            .id(recruit.getId())
            .postType(PostType.RECRUIT.getName())
            .category(recruit.getCategory())
            .title(recruit.getTitle())
            .content(recruit.getContent())
            .amount(recruit.getAmount())
            .now(recruitPositionRepository.countByNow(recruitId))
            .techStacks(recruit.getTechStacks().stream()
                .map(techStack -> RecruitTechStackResponseDto.builder()
                    .techStackId(techStack.getTechStack().getId())
                    .techStack(techStack.getTechStack().getTechStack())
                    .iconUrl(techStack.getTechStack().getIconUrl())
                    .build())
                .collect(Collectors.toList()))
            .build();

        return new SuccessResponseDto<>(true, "통합검색 모집 카드 정보 조회가 완료되었습니다.", recruitCard);
    }

    @Override
    public SuccessResponseDto<CommunityResponseDto> readSearchCommunity(Long communityId) {

        Community community = communityRepository.findByIdAndStatusTrue(communityId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.COMMUNITY_NOT_FOUND));

        CommunityResponseDto communityCard = CommunityResponseDto.builder()
            .id(community.getId())
            .postType(PostType.COMMUNITY.getName())
            .category(community.getCategory())
            .memberId(community.getMember().getId())
            .viewCount(community.getViewCount())
            .createdDate(community.getCreatedDate())
            .modifiedDate(community.getModifiedDate())
            .title(community.getTitle())
            .content(community.getContent())
            .hashTags(community.getCommunityHashTags().stream()
                .map(hashTag -> CommunityHashTagDto.builder()
                    .hashTagId(hashTag.getHashTag().getId())
                    .hashTag(hashTag.getHashTag().getHashTag())
                    .build())
                .collect(Collectors.toList()))
            .build();

        return new SuccessResponseDto<>(true, "통합검색 커뮤니티 카드 정보 조회가 완료되었습니다.", communityCard);
    }
    
}
