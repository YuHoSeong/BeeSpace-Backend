package com.creavispace.project.domain.project.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.creavispace.project.domain.comment.dto.ProjectCommentResponseDto;
import com.creavispace.project.domain.project.entity.Project;

import lombok.Data;

@Data
public class ProjectReadResponseDto {
    private Long id;
    private String field;
    private Long memberId;
    private String memberName;
    private Integer likeCount;
    private Integer viewCount;
    private LocalDateTime createdDate;
    private String title;
    private String content;
    private Boolean like;
    private Boolean bookmark;
    private String kind;
    private List<ProjectMemberResponseDto> memberList;
    private List<ProjectLinkResponseDto> linkList;
    private List<ProjectTechStackResponseDto> techStackList;
    private List<ProjectCommentResponseDto> commentList;

    public ProjectReadResponseDto(Project project){
        this.id = project.getId();
        this.field = project.getField();
        this.memberId = project.getMemberId();
        // this.memberName = project.getMember().getMemberName();
        this.likeCount = project.getLikeList().size();
        this.viewCount = project.getViewCount();
        // this.createdDate = project.getCreatedDate();
        this.title = project.getTitle();
        this.content = project.getContent();
        this.like = false;
        this.bookmark = false;
        this.kind = project.getKind();
        this.memberList = ProjectMemberResponseDto.copyList(project.getMemberList());
        this.linkList = ProjectLinkResponseDto.copyList(project.getLinkList());
        this.techStackList = ProjectTechStackResponseDto.copyList(project.getTechStackList());
        this.commentList = ProjectCommentResponseDto.copyList(project.getCommentList());
    }

}
