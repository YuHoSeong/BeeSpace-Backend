package com.creavispace.project.domain.project.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.creavispace.project.domain.common.entity.TechStack;
import com.creavispace.project.domain.project.entity.ProjectTechStack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechStackResponseDto {
    private Long techStackId;
    private String techStack;
    private String iconUrl;

    public ProjectTechStackResponseDto(ProjectTechStack projectTechStack){
        TechStack techStack = projectTechStack.getTechStack();
        // this.id = projectTechStack.getId();
        this.techStackId = techStack.getId();
        this.techStack = techStack.getTechStack();
        this.iconUrl = techStack.getIconUrl();
    }

    public static List<ProjectTechStackResponseDto> copyList(List<ProjectTechStack> techStackList){
        return techStackList.stream()
            .map(techStack -> new ProjectTechStackResponseDto(techStack))
            .collect(Collectors.toList());
    }
}
