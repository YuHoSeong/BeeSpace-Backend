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
    private Long memberId;
    private ProjectKind kind;
    private String field;
    private String title;
    private String content;
    private String thumbnail;
    private String bannerContent;
    private Integer viewCount;
    private Integer likeCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<ProjectPositionResponseDto> projectPositionList;
    private List<ProjectLinkResponseDto> projectLinkList;
    private List<ProjectTechStackResponseDto> projectTechStackList;

    public ProjectModifyResponseDto(Project project){
        this.id = project.getId();
        this.kind = project.getKind();
        this.field = project.getField();
        this.title = project.getTitle();
        this.content = project.getContent();
        // this.link = project.getLink();
        this.thumbnail = project.getThumbnail();
        this.bannerContent = project.getBannerContent();
        this.viewCount = project.getViewCount();
        this.createdDate = project.getCreatedDate();
        this.modifiedDate = project.getModifiedDate();
        this.projectPositionList = ProjectPositionResponseDto.copyList(project.getMemberList());
        this.projectTechStackList = ProjectTechStackResponseDto.copyList(project.getTechStackList());
    }
}
