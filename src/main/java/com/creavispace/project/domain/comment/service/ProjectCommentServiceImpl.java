package com.creavispace.project.domain.comment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;
import com.creavispace.project.domain.comment.dto.response.ProjectCommentCreateResponseDto;
import com.creavispace.project.domain.comment.entity.ProjectComment;
import com.creavispace.project.domain.comment.repository.ProjectCommentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectCommentServiceImpl implements ProjectCommentService{

    private final ProjectCommentRepository projectCommentRepository;

    @Override
    @Transactional
    public ResponseEntity createProjectComment(ProjectCommentCreateRequestDto dto) {
        // todo : JWT의 MemberId를 작성자로 변경 예정
        Long memberId = 1L;
        ProjectComment projectComment = new ProjectComment(dto, memberId);

        projectCommentRepository.save(projectComment);

        ProjectCommentCreateResponseDto create = new ProjectCommentCreateResponseDto(projectComment);

        // List<ProjectComment> projectCommentList = projectCommentRepository.findByProjectId(dto.getProjectId());
        // List<ProjectCommentCreateResponseDto> create = ProjectCommentCreateResponseDto.copyList(projectCommentList);

        return ResponseEntity.ok().body(create);
    }
    
}
