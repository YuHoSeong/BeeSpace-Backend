package com.creavispace.project.domain.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.comment.dto.request.CommentRequestDto;
import com.creavispace.project.domain.comment.dto.response.CommentDeleteResponseDto;
import com.creavispace.project.domain.comment.dto.response.CommentResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

@Service
public class RecruitCommentServiceImpl implements RecruitCommentService {

    @Override
    public SuccessResponseDto<List<CommentResponseDto>> readRecruitCommentList(Long recruitId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readRecruitComment'");
    }

    @Override
    public SuccessResponseDto<CommentResponseDto> createRecruitComment(Long recruitId, CommentRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRecruitComment'");
    }

    @Override
    public SuccessResponseDto<CommentResponseDto> modifyRecruitComment(Long recruitCommentId, CommentRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyRecruitComment'");
    }

    @Override
    public SuccessResponseDto<CommentDeleteResponseDto> deleteRecruitComment(Long recruitCommentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecruitComment'");
    }
    
}
