package com.creavispace.project.domain.bookmark.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.bookmark.dto.response.ProjectBookmarkReadResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.ProjectBookmarkResponseDto;
import com.creavispace.project.domain.bookmark.entity.ProjectBookmark;
import com.creavispace.project.domain.bookmark.repository.ProjectBookmarkRepository;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
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
public class ProjectBookmarkServiceImpl implements ProjectBookmarkService{
    
    private final ProjectBookmarkRepository projectBookmarkRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public SuccessResponseDto<ProjectBookmarkResponseDto> projectBookmark(Long projectId) {
        // todo : JWT 토큰의 member정보 사용 예정
        Long memberId =1L;

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Project project = optionalProject.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND));

        ProjectBookmark projectBookmark = projectBookmarkRepository.findByProjectIdAndMemberId(projectId, memberId);

        if(projectBookmark == null){
            ProjectBookmark saveBookmark = ProjectBookmark.builder()
                .member(member)
                .project(project)
                .build();
            projectBookmarkRepository.save(saveBookmark);
            return new SuccessResponseDto<ProjectBookmarkResponseDto>(true, "북마크 등록이 완료되었습니다.", new ProjectBookmarkResponseDto(true));
        }else{
            long projectBookmarkId = projectBookmark.getId();
            projectBookmarkRepository.deleteById(projectBookmarkId);
            return new SuccessResponseDto<ProjectBookmarkResponseDto>(true, "북마크 취소가 완료되었습니다.", new ProjectBookmarkResponseDto(false));
        }
    }

    @Override
    public SuccessResponseDto<ProjectBookmarkReadResponseDto> readProjectBookmark(Long projectId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readProjectBookmark'");
    }
    
}
