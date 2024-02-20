package com.creavispace.project.domain.search.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchListReadResponseDto {
    private Long postId;
    private String postType;
}
