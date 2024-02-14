package com.creavispace.project.domain.comment.dto.response;

import java.time.LocalDateTime;

import com.creavispace.project.domain.comment.entity.ProjectComment;
import com.creavispace.project.domain.member.entity.Member;

import lombok.Getter;

@Getter
public class ProjectCommentCreateResponseDto {
    private Long id;
    private Long projectId;
    private Long memberId;
    private String memberNickName;
    private String memberProfileUrl;
    private LocalDateTime modifiedDate;
    private String content;

    public ProjectCommentCreateResponseDto(ProjectComment projectComment){
        Member member = projectComment.getMember();
        this.id = projectComment.getId();
        this.projectId = projectComment.getProject().getId();
        this.memberId = member.getId();
        this.memberNickName = member.getMemberNickname();
        this.memberProfileUrl = member.getProfileUrl();
        this.modifiedDate = projectComment.getModifiedDate();
        this.content = projectComment.getContent();
    }
}
