package com.creavispace.project.domain.community.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommunityDeleteResponseDto {
    private Long communityId;
    private String postType;
}
