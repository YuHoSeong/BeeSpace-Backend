package com.creavispace.project.domain.comment.service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.comment.dto.request.CommentRequestDto;
import com.creavispace.project.domain.comment.dto.response.CommentResponseDto;
import com.creavispace.project.domain.comment.dto.response.CommentDeleteResponseDto;
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
    public SuccessResponseDto<List<CommentResponseDto>> readProjectCommentList(Long projectId) {
        
        List<ProjectComment> projectComments = projectCommentRepository.findByProjectId(projectId);

        List<CommentResponseDto> reads = projectComments.stream()
            .map(projectComment -> CommentResponseDto.builder()
                .id(projectComment.getId())
                .memberId(projectComment.getMember().getId())
                .memberNickName(projectComment.getMember().getMemberNickname())
                .memberProfileUrl(projectComment.getMember().getProfileUrl())
                .modifiedDate(projectComment.getModifiedDate())
                .content(projectComment.getContent())
                .build())
            .collect(Collectors.toList());

        return new SuccessResponseDto<>(true, "해당 프로젝트 댓글 리스트 조회가 완료되었습니다.", reads);
    }

    @Override
    @Transactional
    public SuccessResponseDto<CommentResponseDto> createProjectComment(Long projectId, CommentRequestDto dto) {
        // todo : JWT
        Long memberId = 1L;

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Project project = optionalProject.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND));

        ProjectComment projectComment = ProjectComment.builder()
            .project(project)
            .member(member)
            .content(dto.getContent())
            .build();

        projectCommentRepository.save(projectComment);

        CommentResponseDto create = CommentResponseDto.builder()
            .id(projectComment.getId())
            .memberId(projectComment.getMember().getId())
            .memberNickName(projectComment.getMember().getMemberNickname())
            .memberProfileUrl(projectComment.getMember().getProfileUrl())
            // .modifiedDate(projectComment.getModifiedDate())
            .content(projectComment.getContent())
            .build();

        return new SuccessResponseDto<>(true, "댓글 작성이 완료되었습니다.", create);
    }

    @Override
    @Transactional
    public SuccessResponseDto<CommentResponseDto> modifyProjectComment(Long projectCommentId, CommentRequestDto dto) {
        // todo : JWT
        Long memberId = 1L;
        
        Optional<ProjectComment> optionalProjectComment = projectCommentRepository.findById(projectCommentId);
        ProjectComment projectComment = optionalProjectComment.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.COMMENT_NOT_FOUND));

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));
        
        if(memberId != projectComment.getMember().getId() && !member.getRole().equals("Administrator")){
            new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        projectComment.modify(dto);
        projectCommentRepository.save(projectComment);

        CommentResponseDto modify = CommentResponseDto.builder()
            .id(projectComment.getId())
            .memberId(projectComment.getMember().getId())
            .memberNickName(projectComment.getMember().getMemberNickname())
            .memberProfileUrl(projectComment.getMember().getProfileUrl())
            // .modifiedDate(projectComment.getModifiedDate())
            .content(projectComment.getContent())
            .build();

        return new SuccessResponseDto<>(true, "댓글 수정이 완료되었습니다.", modify);
    }

    @Override
    @Transactional
    public SuccessResponseDto<CommentDeleteResponseDto> deleteProjectComment(Long projectCommentId) {
        // todo : JWT
        Long memberId = 1L;

        Optional<ProjectComment> optionalProjectComment = projectCommentRepository.findById(projectCommentId);
        ProjectComment projectComment = optionalProjectComment.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.COMMENT_NOT_FOUND));

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        if(memberId != projectComment.getMember().getId() && !member.getRole().equals("Administrator")){
            new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        projectCommentRepository.deleteById(projectCommentId);

        return new SuccessResponseDto<>(true, "댓글을 삭제하였습니다.", new CommentDeleteResponseDto(projectCommentId));
    }
    
}
