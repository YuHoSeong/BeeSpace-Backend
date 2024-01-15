package com.creavispace.project.domain.project.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ProjectMemberDto {
    private Long id;
    private Long memberId;
    private String position;

    public static List<Long> modifyIdList(List<ProjectMemberDto> dtoList){
        List<Long> list = new ArrayList<>();
        for(ProjectMemberDto dto : dtoList){
            list.add(dto.getId());
        }
        return list;
    }
}
