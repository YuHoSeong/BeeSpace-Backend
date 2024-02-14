package com.creavispace.project.domain.like.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.FailResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.like.dto.response.ProjectLikeResponseDto;
import com.creavispace.project.domain.like.entity.ProjectLike;
import com.creavispace.project.domain.like.repository.ProjectLikeRepository;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.project.repository.ProjectRepository;

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
    public ResponseEntity<?> projcetLike(Long projectId) {
        // todo : JWT 토큰의 member정보 사용 예정
        Long memberId =1L;

        // 회원 ID로 회원을 찾음
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        // 해당 ID에 대한 회원이 존재하지 않을 경우 실패 응답 반환
        if(optionalMember.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 회원이 존재하지 않습니다.", 400));

        // Optional에서 회원 객체를 가져옴
        Member member = optionalMember.get();

        Optional<Project> optionalProject = projectRepository.findById(projectId);

        // 해당 프로젝트가 존재하지 않을 경우 실패 응답 반환
        if(optionalProject.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 프로젝트가 존재하지 않습니다.", 400));

        // Optional에서 프로젝트 가져옴
        Project project = optionalProject.get();

        ProjectLike projectLike = projectLikeRepository.findByProjectIdAndMemberId(projectId, memberId);

        if(projectLike == null){
            ProjectLike saveLike = ProjectLike.builder()
                .member(member)
                .project(project)
                .build();
            projectLikeRepository.save(saveLike);
            return ResponseEntity.ok().body(new SuccessResponseDto(true, "좋아요 등록이 완료되었습니다.",new ProjectLikeResponseDto(true)));
        }else{
            Long projectLikeId = projectLike.getId();
            projectLikeRepository.deleteById(projectLikeId);
            return ResponseEntity.ok().body(new SuccessResponseDto(true, "좋아요 취소가 완료되었습니다.",new ProjectLikeResponseDto(false)));
        }
    }
    
}
