package com.creavispace.project.domain.project.dto.request;

import java.util.List;

import com.creavispace.project.domain.project.entity.ProjectKind;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateRequestDto {
    private String kind;
    private String field;
    private String title;
    private String content;
    private String thumbnail;
    private String bannerContent;
    private List<ProjectMemberDto> memberList;
    private List<ProjectTechStackDto> techStackList;
    private List<ProjectLinkDto> linkList;

    public ProjectKind getKindAsEnum(){
        try {
            return ProjectKind.valueOf(kind);
        } catch (Exception e) {
            return null;
        }
    }
}
