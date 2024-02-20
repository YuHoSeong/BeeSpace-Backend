package com.creavispace.project.domain.comment.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;
import com.creavispace.project.domain.comment.dto.request.ProjectCommentModifyRequestDto;
import com.creavispace.project.domain.comment.dto.response.ProjectCommentCreateResponseDto;
import com.creavispace.project.domain.comment.dto.response.ProjectCommentModifyResponseDto;
import com.creavispace.project.domain.comment.dto.response.ProjectCommentReadResponseDto;
import com.creavispace.project.domain.comment.entity.ProjectComment;
import com.creavispace.project.domain.comment.repository.ProjectCommentRepository;
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
public class ProjectCommentServiceImpl implements ProjectCommentService{
    
    private final ProjectCommentRepository projectCommentRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Override
    public SuccessResponseDto<List<ProjectCommentReadResponseDto>> readProjectComment(Long projectId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readProjectComment'");
    }

    @Override
    @Transactional
    public SuccessResponseDto<ProjectCommentCreateResponseDto> createProjectComment(ProjectCommentCreateRequestDto dto) {
        // todo : JWT
        Long memberId = 1L;

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Optional<Project> optionalProject = projectRepository.findById(dto.getProjectId());
        Project project = optionalProject.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND));

        ProjectComment projectComment = ProjectComment.builder()
            .project(project)
            .member(member)
            .content(dto.getContent())
            .build();

        projectCommentRepository.save(projectComment);

        ProjectCommentCreateResponseDto create = new ProjectCommentCreateResponseDto(projectComment);

        return new SuccessResponseDto<ProjectCommentCreateResponseDto>(true, "댓글 작성이 완료되었습니다.", create);
    }

    @Override
    @Transactional
    public SuccessResponseDto<ProjectCommentModifyResponseDto> modifyProjectComment(Long projectCommentId, ProjectCommentModifyRequestDto dto) {
        // todo : JWT
        Long memberId = 1L;
        
        Optional<ProjectComment> optionalProjectComment = projectCommentRepository.findById(projectCommentId);
        ProjectComment projectComment = optionalProjectComment.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_COMMENT_NOT_FOUND));

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));
        
        if(memberId != projectComment.getMember().getId() && !member.getRole().equals("Administrator")){
            new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        projectComment.modify(dto);
        projectCommentRepository.save(projectComment);

        ProjectCommentModifyResponseDto modify = new ProjectCommentModifyResponseDto(projectComment);

        return new SuccessResponseDto<ProjectCommentModifyResponseDto>(true, "댓글 수정이 완료되었습니다.", modify);
    }

    @Override
    @Transactional
    public SuccessResponseDto<Long> deleteProjectComment(Long projectCommentId) {
        // todo : JWT
        Long memberId = 1L;

        Optional<ProjectComment> optionalProjectComment = projectCommentRepository.findById(projectCommentId);
        ProjectComment projectComment = optionalProjectComment.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_COMMENT_NOT_FOUND));

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        if(memberId != projectComment.getMember().getId() && !member.getRole().equals("Administrator")){
            new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        projectCommentRepository.deleteById(projectCommentId);

        return new SuccessResponseDto<Long>(true, "댓글을 삭제하였습니다.", projectCommentId);
    }
    
}
