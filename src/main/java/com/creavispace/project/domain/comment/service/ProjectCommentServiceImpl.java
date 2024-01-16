package com.creavispace.project.domain.comment.service;

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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectCommentServiceImpl implements ProjectCommentService{

    private final ProjectCommentRepository projectCommentRepository;

    @Override
    @Transactional
    public ResponseEntity<SuccessResponseDto> createProjectComment(ProjectCommentCreateRequestDto dto) {
        // todo : JWT의 MemberId를 작성자로 변경 예정
        Long memberId = 1L;

        ProjectComment projectComment = new ProjectComment(dto, memberId);
        projectCommentRepository.save(projectComment);

        ProjectCommentCreateResponseDto create = new ProjectCommentCreateResponseDto(projectComment);

        return ResponseEntity.ok().body(new SuccessResponseDto(true, "댓글 작성이 완료되었습니다.", create));
    }

    @Override
    @Transactional
    public ResponseEntity modifyProjectComment(ProjectCommentModifyRequestDto dto) {
        Long projectCommentId = dto.getId();

        ProjectComment projectComment = projectCommentRepository.findById(projectCommentId).orElse(null);

        if(projectComment == null)
            return ResponseEntity.status(404).body(new FailResponseDto(false, "댓글이 존재하지 않습니다.", 404));

        // if(memberId != projectComment.getMemberId() && !member.getRole().equals("Administrator"))
        //     return ResponseEntity.status(401).body(new FailResponseDto(false, "댓글을 수정할 수 있는 권한이 없습니다.", 401);

        projectComment.modify(dto);
        projectCommentRepository.save(projectComment);

        ProjectCommentModifyResponseDto modify = new ProjectCommentModifyResponseDto(projectComment);
        return ResponseEntity.ok().body(new SuccessResponseDto(true, "댓글 수정이 완료되었습니다.", modify));
    }
    
}
