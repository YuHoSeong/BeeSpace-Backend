package com.creavispace.project.domain.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.comment.dto.request.CommentRequestDto;
import com.creavispace.project.domain.comment.dto.response.CommentResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;

@Service
public class CommunityCommentServiceImpl implements CommunityCommentService {

    @Override
    public SuccessResponseDto<List<CommentResponseDto>> readCommunityCommentList(Long communityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readCommunityCommentList'");
    }

    @Override
    public SuccessResponseDto<CommentResponseDto> createCommunityComment(Long communityId, CommentRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCommunityComment'");
    }
    
}
