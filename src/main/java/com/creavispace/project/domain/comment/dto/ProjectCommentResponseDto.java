package com.creavispace.project.domain.comment.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.creavispace.project.domain.comment.entity.ProjectComment;

import lombok.Getter;

@Getter
public class ProjectCommentResponseDto {
    private Long id;
    private Long memberId;
    private String memberName;
    private String memberProfile;
    private String content;
    private LocalDateTime modifiedDate;

    public ProjectCommentResponseDto(ProjectComment projectComment){
        // Member member - projectComment.getMember();
        this.id = projectComment.getId();
        this.memberId = projectComment.getMemberId();
        // this.memberName = member.getMemberName();
        // this.memberProfile = member.getMemberProfile();
        this.content = projectComment.getContent();
        // this.modifiedDate = projectComment.getModifiedDate();
    }

    public static List<ProjectCommentResponseDto> copyList(List<ProjectComment> commentList){
        List<ProjectCommentResponseDto> list = new ArrayList<>();
        if(commentList == null) return list;
        for(ProjectComment projectComment : commentList){
            list.add(new ProjectCommentResponseDto(projectComment));
        }
        return list;
    }
}
