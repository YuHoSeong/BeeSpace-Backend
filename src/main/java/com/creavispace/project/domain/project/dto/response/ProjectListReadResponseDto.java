package com.creavispace.project.domain.project.dto.response;

import java.util.ArrayList;
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
public class ProjectListReadResponseDto {
    private Long id;
    private ProjectKind kind;
    private String title;
    private List<ProjectLinkResponseDto> linkList;
    private String thumbnail;
    private String bannerContent;
    private Boolean like;
    private Boolean bookmark;

    public ProjectListReadResponseDto(Project project){
        this.id = project.getId();
        // this.kind = project.getKind();
        this.title = project.getTitle();
        // this.link = project.getLink();
        this.thumbnail = project.getThumbnail();
        this.bannerContent = project.getBannerContent();
        this.like = false;
        this.bookmark = false;
    }

    public static List<ProjectListReadResponseDto> copyList(List<Project> projectList){
        List<ProjectListReadResponseDto> list = new ArrayList<>();
        if(projectList == null) return list;
        for(Project project : projectList){
            list.add(new ProjectListReadResponseDto(project));
        }
        return list;
    }
}
