package com.creavispace.project.domain.project.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ProjectLinkDto {
    private Long id;
    private String url;
    private String kind;

    public static List<Long> modifyIdList(List<ProjectLinkDto> dtoList){
        List<Long> list = new ArrayList<>();
        for(ProjectLinkDto dto : dtoList){
            list.add(dto.getId());
        }
        return list;
    }
}
