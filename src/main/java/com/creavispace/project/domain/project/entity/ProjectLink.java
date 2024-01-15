package com.creavispace.project.domain.project.entity;

import java.util.ArrayList;
import java.util.List;

import com.creavispace.project.domain.project.dto.request.ProjectLinkDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "project_link")
public class ProjectLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "kind", nullable = false, columnDefinition = "VARCHAR(20) COMMENT 'WEB OR ANDROID OR IOS'")
    private String kind;

    public ProjectLink(ProjectLinkDto dto, Long projectId){
        this.id = dto.getId();
        this.projectId = projectId;
        this.url = dto.getUrl();
        this.kind = dto.getKind();
    }

    public static List<ProjectLink> copyList(List<ProjectLinkDto> urlList, Long projectId){
        List<ProjectLink> list = new ArrayList<>();
        if(urlList == null) return list;
        for(ProjectLinkDto dto : urlList){
            list.add(new ProjectLink(dto, projectId));
        }
        return list;
    }
}