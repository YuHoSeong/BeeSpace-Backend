package com.creavispace.project.domain.project.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.creavispace.project.domain.project.entity.Project;

import lombok.Getter;

@Getter
public class ProjectListReadResponseDto {
    private Long id;
    private String kind;
    private String title;
    private String thumbnail;
    private String bannerContent;
    private Boolean like;
    private Boolean bookmark;
    private List<ProjectLinkResponseDto> linkList;

    public ProjectListReadResponseDto(Project project){
        this.id = project.getId();
        this.kind = project.getKind();
        this.title = project.getTitle();
        this.thumbnail = project.getThumbnail();
        this.bannerContent = project.getBannerContent();
        this.linkList = ProjectLinkResponseDto.copyList(project.getLinkList());
        this.like = false;
        this.bookmark = false;
    }

    public static List<ProjectListReadResponseDto> copyList(List<Project> projectList){
        List<ProjectListReadResponseDto> list = new ArrayList<>();
        for(Project project : projectList){
            list.add(new ProjectListReadResponseDto(project));
        }
        return list;
    }
    
    public void patchLike(boolean like){
        this.like = like;
    }

    public void patchBookmark(boolean bookmark){
        this.bookmark = bookmark;
    }
}
