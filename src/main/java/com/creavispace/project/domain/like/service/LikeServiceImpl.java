package com.creavispace.project.domain.like.service;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.PostType;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.LikeCountResponseDto;
import com.creavispace.project.domain.like.repository.CommunityLikeRepository;
import com.creavispace.project.domain.like.repository.ProjectLikeRepository;
import com.creavispace.project.global.exception.CreaviCodeException;
import com.creavispace.project.global.exception.GlobalErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final CommunityLikeRepository communityLikeRepository;
    private final ProjectLikeRepository projectLikeRepository;

    @Override
    public SuccessResponseDto<LikeCountResponseDto> likeCount(String postType, Long postId) {
        Integer likeCount;
        if(postType.equals(PostType.COMMUNITY.getName())){
            likeCount = communityLikeRepository.countByCommunityId(postId);
        }else if(postType.equals(PostType.PROJECT.getName())){
            likeCount = projectLikeRepository.countByProjectId(postId);
        }else{
            throw new CreaviCodeException(GlobalErrorCode.LIKE_COUNT_NOT_FOUND);
        }

        return new SuccessResponseDto<>(Boolean.TRUE, "좋아요 수 조회가 완료되었습니다.", new LikeCountResponseDto(likeCount));
    }
    
}
