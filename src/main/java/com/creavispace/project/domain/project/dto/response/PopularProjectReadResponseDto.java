package com.creavispace.project.domain.project.dto.response;

import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.project.entity.ProjectKind;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularProjectReadResponseDto {
    private Long id;
    private String title;
    private String thumbnail;
    private ProjectKind kind;
    private String bannerContent;

    public PopularProjectReadResponseDto(Project project){
        this.id = project.getId();
        this.title = project.getTitle();
        this.thumbnail = project.getThumbnail();
        this.kind = project.getKind();
        this.bannerContent = project.getBannerContent();
    }
}
