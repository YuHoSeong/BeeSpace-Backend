package com.creavispace.project.domain.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopularProjectReadResponseDto {
    private Long id;
    private String postType;
    private String title;
    private String thumbnail;
    private String category;
    private String bannerContent;

}
