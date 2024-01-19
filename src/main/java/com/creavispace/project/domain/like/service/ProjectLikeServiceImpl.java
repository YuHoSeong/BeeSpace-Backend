package com.creavispace.project.domain.like.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.bookmark.entity.ProjectBookmark;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.ProjectLikeResponseDto;
import com.creavispace.project.domain.like.entity.ProjectLike;
import com.creavispace.project.domain.like.repository.ProjectLikeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectLikeServiceImpl implements ProjectLikeService{

    private final ProjectLikeRepository projectLikeRepository;
    
    @Override
    @Transactional
    public ResponseEntity projcetLike(Long projectId) {
        // todo : JWT 토큰의 member정보 사용 예정
        Long memberId =1L;
        ProjectLikeResponseDto projectLikeResponseDto;

        ProjectLike projectLike = projectLikeRepository.findByProjectIdAndMemberId(projectId, memberId);

        if(projectLike == null){
            projectLike = new ProjectLike(projectId, memberId);
            projectLikeRepository.save(projectLike);
            projectLikeResponseDto = new ProjectLikeResponseDto(true);
        }else{
            long projectLikeId = projectLike.getId();
            projectLikeRepository.deleteById(projectLikeId);
            projectLikeResponseDto = new ProjectLikeResponseDto(false);
        }
        
        return ResponseEntity.ok().body(new SuccessResponseDto(true, "프로젝트 좋아요 등록 or 삭제가 완료되었습니다.",projectLikeResponseDto));
    }
    
}
