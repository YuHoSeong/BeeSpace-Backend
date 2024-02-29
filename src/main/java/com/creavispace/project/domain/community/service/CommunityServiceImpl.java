package com.creavispace.project.domain.community.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.PostType;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.dto.request.CommunityRequestDto;
import com.creavispace.project.domain.community.dto.response.CommunityResponseDto;
import com.creavispace.project.domain.community.entity.Community;
import com.creavispace.project.domain.community.entity.CommunityHashTag;
import com.creavispace.project.domain.community.repository.CommunityHashTagRepository;
import com.creavispace.project.domain.community.repository.CommunityRepository;
import com.creavispace.project.domain.hashTag.entity.HashTag;
import com.creavispace.project.domain.hashTag.repository.HashTagRepository;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.global.exception.CreaviCodeException;
import com.creavispace.project.global.exception.GlobalErrorCode;

import lombok.RequiredArgsConstructor;

import com.creavispace.project.domain.community.dto.response.CommunityDeleteResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityHashTagDto;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{

    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;
    private final HashTagRepository hashTagRepository;
    private final CommunityHashTagRepository communityHashTagRepository;

    @Override
    public SuccessResponseDto<CommunityResponseDto> createCommunity(CommunityRequestDto dto) {
        Long memberId = 1L;
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Community community = Community.builder()
            .member(member)
            .category(dto.getCategory())
            .title(dto.getTitle())
            .content(dto.getContent())
            .viewCount(0)
            .status(Boolean.TRUE)
            .build();

        communityRepository.save(community);

        List<String> hashTagDtos = dto.getHashTags();

        if(hashTagDtos != null && hashTagDtos.isEmpty()){
            List<CommunityHashTag> communityHashTags = dto.getHashTags().stream()
                .map(hashTagDto -> {
                    Boolean hasHashTag = hashTagRepository.existsByHashTag(hashTagDto);
                    HashTag hashTag;
                    if(hasHashTag){
                        hashTag = hashTagRepository.findByHashTag(hashTagDto);
                    }else{
                        hashTag = hashTagRepository.save(HashTag.builder().hashTag(hashTagDto).build());
                    }
                    return CommunityHashTag.builder()
                    .community(community)
                    .hashTag(hashTag)
                    .build();
                })
                .collect(Collectors.toList());
            
            communityHashTagRepository.saveAll(communityHashTags);
        }

        List<CommunityHashTag> communityHashTags = communityHashTagRepository.findByCommunityId(community.getId());

        List<CommunityHashTagDto> communityHashTagDtos = communityHashTags.stream()
            .map(communityHashTag -> CommunityHashTagDto.builder()
                .hashTagId(communityHashTag.getHashTag().getId())
                .hashTag(communityHashTag.getHashTag().getHashTag())
                .build())
            .collect(Collectors.toList());
        
        CommunityResponseDto create = CommunityResponseDto.builder()
            .id(community.getId())
            .postType(PostType.COMMUNITY.getName())
            .category(community.getCategory())
            .memberId(community.getMember().getId())
            .viewCount(community.getViewCount())
            .createdDate(community.getCreatedDate())
            .modifiedDate(community.getModifiedDate())
            .title(community.getTitle())
            .content(community.getContent())
            .hashTags(communityHashTagDtos)
            .build();

        return new SuccessResponseDto<>(true, "커뮤니티 게시글 생성이 완료되었습니다.", create);
        
    }

    @Override
    public SuccessResponseDto<CommunityResponseDto> modifyCommunity(Long communityId,
        CommunityRequestDto dto) {
        //JWT
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));
        Community community = communityRepository.findById(communityId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.COMMUNITY_NOT_FOUND));

        if(community.getMember().getId() != memberId && !member.getRole().equals("Administrator")){
            new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        Community modifyCommunity = community.toBuilder()
            .category(dto.getCategory())
            .title(dto.getTitle())
            .content(dto.getContent())
            .build();

        communityRepository.save(modifyCommunity);

        communityHashTagRepository.deleteByCommunityId(communityId);

        List<String> hashTagDtos = dto.getHashTags();

        if(hashTagDtos != null && hashTagDtos.isEmpty()){
            List<CommunityHashTag> communityHashTags = hashTagDtos.stream()
                .map(hashTagDto -> {
                    Boolean hasHashTag = hashTagRepository.existsByHashTag(hashTagDto);
                    HashTag hashTag;
                    if(hasHashTag){
                        hashTag = hashTagRepository.findByHashTag(hashTagDto);
                    }else{
                        hashTag = hashTagRepository.save(HashTag.builder().hashTag(hashTagDto).build());
                    }
                    return CommunityHashTag.builder()
                    .community(community)
                    .hashTag(hashTag)
                    .build();
                })
                .collect(Collectors.toList());
            
            communityHashTagRepository.saveAll(communityHashTags);
        }

        List<CommunityHashTag> communityHashTags = communityHashTagRepository.findByCommunityId(communityId);

        List<CommunityHashTagDto> hashTags = communityHashTags.stream()
            .map(communityHashTag -> CommunityHashTagDto.builder()
                .hashTagId(communityHashTag.getHashTag().getId())
                .hashTag(communityHashTag.getHashTag().getHashTag())
                .build())
            .collect(Collectors.toList());

        CommunityResponseDto modify = CommunityResponseDto.builder()
            .id(modifyCommunity.getId())
            .postType(PostType.COMMUNITY.getName())
            .category(modifyCommunity.getCategory())
            .memberId(modifyCommunity.getMember().getId())
            .viewCount(modifyCommunity.getViewCount())
            .createdDate(modifyCommunity.getCreatedDate())
            .modifiedDate(modifyCommunity.getModifiedDate())
            .title(modifyCommunity.getTitle())
            .content(modifyCommunity.getContent())
            .hashTags(hashTags)
            .build();

        return new SuccessResponseDto<>(true, "커뮤니티 게시글 수정이 완료되었습니다.", modify);

    }

    @Override
    public SuccessResponseDto<CommunityDeleteResponseDto> deleteCommunity(Long communityId) {
        // JWT 토큰
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));
        Community community = communityRepository.findById(communityId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.COMMUNITY_NOT_FOUND));
        if(community.getMember().getId() != memberId && !member.getRole().equals("Administrator")){
            new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        Community disableCommunity = community.toBuilder()
            .status(false)
            .build();

        communityRepository.save(disableCommunity);

        CommunityDeleteResponseDto delete = CommunityDeleteResponseDto.builder()
            .communityId(disableCommunity.getId())
            .postType(PostType.COMMUNITY.getName())
            .build();

        return new SuccessResponseDto<>(true, "커뮤니티 게시글 삭제가 완료되었습니다.", delete);
    }

    @Override
    public SuccessResponseDto<CommunityResponseDto> readCommunity(Long communityId) {
        Community community = communityRepository.findByIdAndStatusTrue(communityId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.COMMUNITY_NOT_FOUND));

        // if(isJwt){
        //     community = communityRepository.findById(communityId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.COMMUNITY_NOT_FOUND));
        // }

        CommunityResponseDto read = CommunityResponseDto.builder()
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

        return new SuccessResponseDto<>(true, "커뮤니티 게시글 디테일 조회가 완료되었습니다.", read);
    }

    @Override
    public SuccessResponseDto<List<CommunityResponseDto>> readCommunityList(Integer size, Integer page, String category, String hashTag) {
        Pageable pageRequest = PageRequest.of(page-1, size);
        Page<Community> pageable;

        if(category != null && !category.isEmpty() && hashTag != null && !hashTag.isEmpty()){
            Long hashTagId = hashTagRepository.findByHashTag(hashTag).getId();
            pageable = communityRepository.findAllByStatusTrueAndCategoryAndHashTagId(category, hashTagId, pageRequest);
        }else if((category == null || category.isEmpty()) && hashTag != null && !hashTag.isEmpty()){
            Long hashTagId = hashTagRepository.findByHashTag(hashTag).getId();
            pageable = communityRepository.findAllByStatusTrueAndHashTagId(hashTagId, pageRequest);
        }else if((hashTag == null || hashTag.isEmpty()) && category != null && !category.isEmpty()){
            pageable = communityRepository.findAllByStatusTrueAndCategory(category, pageRequest);
        }else{
            pageable = communityRepository.findAllByStatusTrue(pageRequest);
        }

        if(!pageable.hasContent()) new CreaviCodeException(GlobalErrorCode.NOT_COMMUNITY_CONTENT);
        List<Community> communities = pageable.getContent();

        List<CommunityResponseDto> reads = communities.stream()
            .map(community -> CommunityResponseDto.builder()
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
                    .map(communityHashTag -> CommunityHashTagDto.builder()
                        .hashTagId(communityHashTag.getHashTag().getId())
                        .hashTag(communityHashTag.getHashTag().getHashTag())
                        .build())
                    .collect(Collectors.toList()))
                .build())
            .collect(Collectors.toList());

        return new SuccessResponseDto<>(true, "커뮤니티 게시글 리스트 조회가 완료되었습니다.", reads);
                
    }
    
}
