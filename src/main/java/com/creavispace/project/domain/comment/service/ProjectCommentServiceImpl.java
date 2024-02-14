package com.creavispace.project.domain.comment.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;
import com.creavispace.project.domain.comment.dto.request.ProjectCommentModifyRequestDto;
import com.creavispace.project.domain.comment.dto.response.ProjectCommentCreateResponseDto;
import com.creavispace.project.domain.comment.dto.response.ProjectCommentModifyResponseDto;
import com.creavispace.project.domain.comment.entity.ProjectComment;
import com.creavispace.project.domain.comment.repository.ProjectCommentRepository;
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
public class ProjectCommentServiceImpl implements ProjectCommentService{

    private final ProjectCommentRepository projectCommentRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public ResponseEntity<?> createProjectComment(ProjectCommentCreateRequestDto dto) {
        // todo : JWT
        Long memberId = 1L;

        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if(optionalMember.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 회원이 존재하지 않습니다.", 400));

        Member member = optionalMember.get();

        Optional<Project> optionalProject = projectRepository.findById(dto.getProjectId());

        if(optionalProject.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 프로젝트가 존재하지 않습니다.", 400));

        Project project = optionalProject.get();

        ProjectComment projectComment = ProjectComment.builder()
            .project(project)
            .member(member)
            .content(dto.getContent())
            .build();

        projectCommentRepository.save(projectComment);

        ProjectCommentCreateResponseDto create = new ProjectCommentCreateResponseDto(projectComment);

        return ResponseEntity.ok().body(new SuccessResponseDto(true, "댓글 작성이 완료되었습니다.", create));
    }

    @Override
    @Transactional
    public ResponseEntity<?> modifyProjectComment(ProjectCommentModifyRequestDto dto) {
        // todo : JWT
        Long memberId = 1L;
        Long projectCommentId = dto.getId();
        
        Optional<ProjectComment> optionalProjectComment = projectCommentRepository.findById(projectCommentId);
        if(optionalProjectComment.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 댓글이 존재하지 않습니다.", 400));
        ProjectComment projectComment= optionalProjectComment.get();

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 회원이 존재하지 않습니다.", 400));
        Member member = optionalMember.get();
        
        if(memberId != projectComment.getMember().getId() && !member.getRole().equals("Administrator"))
            return ResponseEntity.status(401).body(new FailResponseDto(false, "댓글을 수정할 수 있는 권한이 없습니다.", 401));

        projectComment.modify(dto);
        projectCommentRepository.save(projectComment);

        ProjectCommentModifyResponseDto modify = new ProjectCommentModifyResponseDto(projectComment);

        return ResponseEntity.ok().body(new SuccessResponseDto(true, "댓글 수정이 완료되었습니다.", modify));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteProjectComment(Long projectCommentId) {
        // todo : JWT
        Long memberId = 1L;

        Optional<ProjectComment> optionalProjectComment = projectCommentRepository.findById(projectCommentId);
        if(optionalProjectComment.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 댓글이 존재하지 않습니다.", 400));
        ProjectComment projectComment= optionalProjectComment.get();

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 회원이 존재하지 않습니다.", 400));
        Member member = optionalMember.get();

        if(memberId != projectComment.getMember().getId() && !member.getRole().equals("Administrator"))
            return ResponseEntity.status(401).body(new FailResponseDto(false, "댓글을 삭제할 수 있는 권한이 없습니다.", 401));

        projectCommentRepository.deleteById(projectCommentId);

        return ResponseEntity.ok().body(new SuccessResponseDto(true, "댓글을 삭제하였습니다.", projectCommentId));
    }
    
}
