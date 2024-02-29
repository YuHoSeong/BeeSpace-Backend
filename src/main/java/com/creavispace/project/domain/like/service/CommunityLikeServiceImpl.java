package com.creavispace.project.domain.like.service;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.entity.Community;
import com.creavispace.project.domain.community.repository.CommunityRepository;
import com.creavispace.project.domain.like.dto.response.LikeResponseDto;
import com.creavispace.project.domain.like.entity.CommunityLike;
import com.creavispace.project.domain.like.repository.CommunityLikeRepository;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.global.exception.CreaviCodeException;
import com.creavispace.project.global.exception.GlobalErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityLikeServiceImpl implements CommunityLikeService {

    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;
    private final CommunityLikeRepository communityLikeRepository;

    @Override
    public SuccessResponseDto<LikeResponseDto> communityLike(Long communityId) {
        // JWT
        Long memberId =1L;

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Community community = communityRepository.findById(communityId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.COMMUNITY_NOT_FOUND));

        CommunityLike communityLike = communityLikeRepository.findByCommunityIdAndMemberId(communityId, memberId);

        if(communityLike == null){
            CommunityLike saveLike = CommunityLike.builder()
                .member(member)
                .community(community)
                .build();
            communityLikeRepository.save(saveLike);
            return new SuccessResponseDto<>(true, "좋아요 등록이 완료되었습니다.", new LikeResponseDto(true));
        }else{
            Long communityLikeId = communityLike.getId();
            communityLikeRepository.deleteById(communityLikeId);
            return new SuccessResponseDto<>(true, "좋아요 취소가 완료되었습니다.", new LikeResponseDto(false));
        }
    }

    @Override
    public SuccessResponseDto<LikeResponseDto> readCommunityLike(Long communityId) {
        // JWT
        Long memberId = 1L;

        Boolean isMember = memberRepository.existsById(memberId);
        if(!isMember) new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND);

        Boolean isCommunity = communityRepository.existsById(communityId);
        if(!isCommunity) new CreaviCodeException(GlobalErrorCode.COMMUNITY_NOT_FOUND);

        Boolean isCommunityLike = communityLikeRepository.existsByCommunityIdAndMemberId(communityId, memberId);

        return new SuccessResponseDto<>(true, "좋아요 조회가 완료되었습니다.", new LikeResponseDto(isCommunityLike));
    }
    
}
