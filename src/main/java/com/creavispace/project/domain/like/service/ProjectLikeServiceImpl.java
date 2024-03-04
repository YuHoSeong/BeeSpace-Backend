package com.creavispace.project.domain.like.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.LikeResponseDto;
import com.creavispace.project.domain.like.entity.ProjectLike;
import com.creavispace.project.domain.like.repository.ProjectLikeRepository;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.project.repository.ProjectRepository;
import com.creavispace.project.global.exception.CreaviCodeException;
import com.creavispace.project.global.exception.GlobalErrorCode;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectLikeServiceImpl implements ProjectLikeService{

    private final ProjectLikeRepository projectLikeRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    
    @Override
    @Transactional
    public SuccessResponseDto<LikeResponseDto> projectLike(Long memberId, Long projectId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Project project = optionalProject.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND));

        ProjectLike projectLike = projectLikeRepository.findByProjectIdAndMemberId(projectId, memberId);

        if(projectLike == null){
            ProjectLike saveLike = ProjectLike.builder()
                .member(member)
                .project(project)
                .build();
            projectLikeRepository.save(saveLike);
            return new SuccessResponseDto<LikeResponseDto>(true, "좋아요 등록이 완료되었습니다.",new LikeResponseDto(true));
        }else{
            Long projectLikeId = projectLike.getId();
            projectLikeRepository.deleteById(projectLikeId);
            return new SuccessResponseDto<LikeResponseDto>(true, "좋아요 취소가 완료되었습니다.",new LikeResponseDto(false));
        }
    }

    @Override
    public SuccessResponseDto<LikeResponseDto> readProjectLike(Long memberId, Long projectId) {
        Boolean isMember = memberRepository.existsById(memberId);
        if(!isMember) throw new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND);

        Boolean isProject = projectRepository.existsById(projectId);
        if(!isProject) throw new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND);

        Boolean isProjectLike = projectLikeRepository.existsByProjectIdAndMemberId(projectId, memberId);

        return new SuccessResponseDto<>(true, "좋아요 조회가 완료되었습니다.", new LikeResponseDto(isProjectLike));
    }
    
}
