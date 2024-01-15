package com.creavispace.project.domain.project.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.creavispace.project.domain.project.entity.Project;

import lombok.Getter;

@Getter
public class PopularProjectReadResponseDto {
    private Long id;
    private String title;
    private String thumbnail;
    private String kind;
    private String bannerContent;

    public PopularProjectReadResponseDto(Project project){
        this.id = project.getId();
        this.title = project.getTitle();
        this.thumbnail = project.getThumbnail();
        this.kind = project.getKind();
        this.bannerContent = project.getBannerContent();
    }

    public static List<PopularProjectReadResponseDto> copyList(List<Project> projectList){
        List<PopularProjectReadResponseDto> list = new ArrayList<>();
        for(Project project : projectList){
            list.add(new PopularProjectReadResponseDto(project));
        }
        return list;
    }
}
