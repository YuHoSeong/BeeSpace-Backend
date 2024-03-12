package com.creavispace.project.domain.comment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.comment.dto.request.CommentRequestDto;
import com.creavispace.project.domain.comment.dto.response.CommentDeleteResponseDto;
import com.creavispace.project.domain.comment.dto.response.CommentResponseDto;
import com.creavispace.project.domain.comment.entity.CommunityComment;
import com.creavispace.project.domain.comment.entity.ProjectComment;
import com.creavispace.project.domain.comment.entity.RecruitComment;
import com.creavispace.project.domain.comment.repository.CommunityCommentRepository;
import com.creavispace.project.domain.comment.repository.ProjectCommentRepository;
import com.creavispace.project.domain.comment.repository.RecruitCommentRepository;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.global.exception.CreaviCodeException;
import com.creavispace.project.global.exception.GlobalErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ProjectCommentRepository projectCommentRepository;
    private final RecruitCommentRepository recruitCommentRepository;
    private final CommunityCommentRepository communityCommentRepository;

    @Override
    public SuccessResponseDto<List<CommentResponseDto>> readCommentList(Long postId, String type) {
        
        List<CommentResponseDto> data;

        switch (type) {
            case "project":
                List<ProjectComment> projectComments = projectCommentRepository.findByProjectId(postId);
                data = projectComments.stream()
                    .map(projectComment -> CommentResponseDto.builder()
                        .id(projectComment.getId())
                        .memberId(projectComment.getMember().getId())
                        .memberNickName(projectComment.getMember().getMemberNickname())
                        .memberProfileUrl(projectComment.getMember().getProfileUrl())
                        .modifiedDate(projectComment.getModifiedDate())
                        .content(projectComment.getContent())
                        .build())
                    .collect(Collectors.toList());
                break;
        
            case "community":
                List<CommunityComment> communityComments = communityCommentRepository.findByCommunityId(postId);
                data = communityComments.stream()
                    .map(communityComment -> CommentResponseDto.builder()
                        .id(communityComment.getId())
                        .memberId(communityComment.getMember().getId())
                        .memberNickName(communityComment.getMember().getMemberNickname())
                        .memberProfileUrl(communityComment.getMember().getProfileUrl())
                        .modifiedDate(communityComment.getModifiedDate())
                        .content(communityComment.getContent())
                        .build())
                    .collect(Collectors.toList());
                break;
                
            case "recruit":
                List<RecruitComment> recruitComments = recruitCommentRepository.findByRecruitId(postId);
                data = recruitComments.stream()
                    .map(recruitComment -> CommentResponseDto.builder()
                        .id(recruitComment.getId())
                        .memberId(recruitComment.getMember().getId())
                        .memberNickName(recruitComment.getMember().getMemberNickname())
                        .memberProfileUrl(recruitComment.getMember().getProfileUrl())
                        .modifiedDate(recruitComment.getModifiedDate())
                        .content(recruitComment.getContent())
                        .build())
                    .collect(Collectors.toList());
                break;
            default:
                throw new CreaviCodeException(GlobalErrorCode.TYPE_NOT_FOUND);
        }

        return new SuccessResponseDto<>(false, "댓글 리스트 조회가 완료되었습니다.", data);
    }

    @Override
    public SuccessResponseDto<CommentResponseDto> createComment(Long postId, String type, CommentRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createComment'");
    }

    @Override
    public SuccessResponseDto<CommentResponseDto> modifyComment(Long commentId, CommentRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyComment'");
    }

    @Override
    public SuccessResponseDto<CommentDeleteResponseDto> deleteComment(Long commentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteComment'");
    }

}
