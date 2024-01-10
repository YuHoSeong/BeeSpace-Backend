package com.creavispace.project.domain.project.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.creavispace.project.domain.project.entity.ProjectLink;

import lombok.Data;

@Data
public class ProjectLinkResponseDto {
    private Long id;
    private String kind;
    private String url;
    
    public ProjectLinkResponseDto(ProjectLink projectLink){
        this.id = projectLink.getId();
        this.kind = projectLink.getKind();
        this.url = projectLink.getUrl();
    }

    public static List<ProjectLinkResponseDto> copyList(List<ProjectLink> LinkList){
        List<ProjectLinkResponseDto> list = new ArrayList<>();
        for(ProjectLink projectLink : LinkList){
            list.add(new ProjectLinkResponseDto(projectLink));
        }
        return list;
    }
}
