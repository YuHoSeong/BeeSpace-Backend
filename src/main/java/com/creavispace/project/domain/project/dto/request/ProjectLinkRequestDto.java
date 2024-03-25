package com.creavispace.project.domain.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectLinkRequestDto {
    // enum 관리로 변경
    private String linkType;
    private String url;
}
