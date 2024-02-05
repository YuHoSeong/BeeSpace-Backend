package com.creavispace.project.domain.project.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.creavispace.project.domain.project.entity.ProjectTechStack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechStackResponseDto {
    private Long id;
    private Long techStackId;
    private String techStackName;
    private String url;

    public ProjectTechStackResponseDto(ProjectTechStack projectTechStack){
        // TechStack techStack = projectTechStack.getTechStack();
        this.id = projectTechStack.getId();
        // this.techStackId = techStack.getId();
        // this.techStackName = techStack.getTechStackName();
        // this.url = techStack.getUrl();
    }

    public static List<ProjectTechStackResponseDto> copyList(List<ProjectTechStack> techStackList){
        return techStackList.stream()
            .map(techStack -> new ProjectTechStackResponseDto(techStack))
            .collect(Collectors.toList());
    }
}
