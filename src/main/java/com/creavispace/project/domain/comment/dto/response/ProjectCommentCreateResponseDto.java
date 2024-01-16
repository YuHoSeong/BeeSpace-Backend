package com.creavispace.project.domain.comment.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.creavispace.project.domain.comment.entity.ProjectComment;

import lombok.Getter;

@Getter
public class ProjectCommentCreateResponseDto {
    private Long id;
    private Long projectId;
    private Long memberId;
    private String memberName;
    private String memberProfile;
    private LocalDateTime modifiedDate;
    private String content;

    public ProjectCommentCreateResponseDto(ProjectComment projectComment){
        // Member member = projectComment.getMember();
        this.id = projectComment.getId();
        this.projectId = projectComment.getProjectId();
        this.memberId = projectComment.getMemberId();
        // this.memberName = member.getMemberName();
        // this.memberProfile = member.getMemberProfile();
        // this.modifiedDate = projectComment.getModifiedDate();
        this.content = projectComment.getContent();
    }

    public static List<ProjectCommentCreateResponseDto> copyList(List<ProjectComment> projectCommentList){
        List<ProjectCommentCreateResponseDto> list = new ArrayList<>();
        if(projectCommentList == null) return list;
        for(ProjectComment projectComment : projectCommentList){
            list.add(new ProjectCommentCreateResponseDto(projectComment));
        }
        return list;
    }
}
