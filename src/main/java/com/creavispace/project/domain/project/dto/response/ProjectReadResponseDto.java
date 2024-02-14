package com.creavispace.project.domain.project.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.project.entity.ProjectKind;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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
    private String link;
    private Boolean like;
    private Boolean bookmark;
    private ProjectKind kind;
    private List<ProjectMemberResponseDto> projectMemberList;
    private List<ProjectTechStackResponseDto> projectTechStackList;
    private List<ProjectCommentResponseDto> projectCommentList;

    public ProjectReadResponseDto(Project project){
        this.id = project.getId();
        this.field = project.getField();
        this.memberId = project.getMember().getId();
        // this.memberName = project.getMember().getMemberName();
        this.likeCount = project.getLikeList().size();
        this.viewCount = project.getViewCount();
        this.createdDate = project.getCreatedDate();
        this.title = project.getTitle();
        this.content = project.getContent();
        this.link = project.getLink();
        this.like = false;
        this.bookmark = false;
        this.kind = project.getKind();
        this.projectMemberList = ProjectMemberResponseDto.copyList(project.getMemberList());
        this.projectTechStackList = ProjectTechStackResponseDto.copyList(project.getTechStackList());
        this.projectCommentList = ProjectCommentResponseDto.copyList(project.getCommentList());
    }

}
