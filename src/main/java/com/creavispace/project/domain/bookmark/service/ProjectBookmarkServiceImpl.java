package com.creavispace.project.domain.bookmark.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.bookmark.dto.response.ProjectBookmarkResponseDto;
import com.creavispace.project.domain.bookmark.entity.ProjectBookmark;
import com.creavispace.project.domain.bookmark.repository.ProjectBookmarkRepository;
import com.creavispace.project.domain.common.dto.FailResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.project.repository.ProjectRepository;

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
    public ResponseEntity<?> projectBookmark(Long projectId) {
        // todo : JWT 토큰의 member정보 사용 예정
        Long memberId =1L;

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 회원이 존재하지 않습니다.", 400));
        Member member = optionalMember.get();

        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if(optionalProject.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 프로젝트가 존재하지 않습니다.", 400));
        Project project = optionalProject.get();

        ProjectBookmark projectBookmark = projectBookmarkRepository.findByProjectIdAndMemberId(projectId, memberId);

        if(projectBookmark == null){
            ProjectBookmark saveBookmark = ProjectBookmark.builder()
                .member(member)
                .project(project)
                .build();
            projectBookmarkRepository.save(saveBookmark);
            return ResponseEntity.ok().body(new SuccessResponseDto(true, "북마크 등록이 완료되었습니다.", new ProjectBookmarkResponseDto(true)));
        }else{
            long projectBookmarkId = projectBookmark.getId();
            projectBookmarkRepository.deleteById(projectBookmarkId);
            return ResponseEntity.ok().body(new SuccessResponseDto(true, "북마크 취소가 완료되었습니다.", new ProjectBookmarkResponseDto(false)));
        }
    }
    
}
