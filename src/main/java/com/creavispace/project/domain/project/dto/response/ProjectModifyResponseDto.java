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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectModifyResponseDto {
    private Long id;
    private ProjectKind kind;
    private String field;
    private String title;
    private String content;
    private String link;
    private String thumbnail;
    private String bannerContent;
    private Integer viewCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<ProjectMemberResponseDto> projectMemberList;
    private List<ProjectTechStackResponseDto> projectTechStackList;

    public ProjectModifyResponseDto(Project project){
        this.id = project.getId();
        this.kind = project.getKind();
        this.field = project.getField();
        this.title = project.getTitle();
        this.content = project.getContent();
        this.link = project.getLink();
        this.thumbnail = project.getThumbnail();
        this.bannerContent = project.getBannerContent();
        this.viewCount = project.getViewCount();
        this.createdDate = project.getCreatedDate();
        this.modifiedDate = project.getModifiedDate();
        this.projectMemberList = ProjectMemberResponseDto.copyList(project.getMemberList());
        this.projectTechStackList = ProjectTechStackResponseDto.copyList(project.getTechStackList());
    }
}
