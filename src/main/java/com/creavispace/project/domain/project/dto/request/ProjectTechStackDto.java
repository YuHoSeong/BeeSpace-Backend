package com.creavispace.project.domain.project.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ProjectTechStackDto {
    private Long id;
    private Long techStackId;

    public static List<Long> modifyIdList(List<ProjectTechStackDto> dtoList){
        List<Long> list = new ArrayList<>();
        for(ProjectTechStackDto dto : dtoList){
            list.add(dto.getId());
        }
        return list;
    }
}
