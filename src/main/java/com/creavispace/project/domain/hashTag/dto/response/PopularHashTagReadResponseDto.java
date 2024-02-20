package com.creavispace.project.domain.hashTag.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopularHashTagReadResponseDto {
    private Long id;
    private String hashTag;
}
