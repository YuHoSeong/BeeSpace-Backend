package com.creavispace.project.domain.comment.dto.response;

import java.time.LocalDateTime;

import com.creavispace.project.domain.comment.entity.ProjectComment;

import lombok.Getter;

@Getter
public class ProjectCommentModifyResponseDto {
    private Long id;
    private Long projectId;
    private Long memberId;
    private String memberName;
    private String memberProfile;
    private LocalDateTime modifiedDate;
    private String content;

    public ProjectCommentModifyResponseDto(ProjectComment projectComment){
        // Member member = projectComment.getMember();
        this.id = projectComment.getId();
        this.projectId = projectComment.getProjectId();
        this.memberId = projectComment.getMemberId();
        // this.memberName = member.getMemberName();
        // this.memberProfile = member.getMemberProfile();
        // this.modifiedDate = projectComment.getModifiedDate();
        this.content = projectComment.getContent();
    }
}
