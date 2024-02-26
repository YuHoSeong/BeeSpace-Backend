package com.creavispace.project.domain.project.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProjectListReadResponseDto {
    private Long id;
    private String postType;
    private String category;
    private String title;
    private List<ProjectLinkResponseDto> links;
    private String thumbnail;
    private String bannerContent;

}
