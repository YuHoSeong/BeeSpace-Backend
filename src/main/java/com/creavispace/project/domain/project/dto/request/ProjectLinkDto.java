package com.creavispace.project.domain.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectLinkDto {
    private Long id;
    private String url;
    private String kind;

}
