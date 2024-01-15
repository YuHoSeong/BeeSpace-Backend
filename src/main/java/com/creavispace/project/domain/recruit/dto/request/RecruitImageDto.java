package com.creavispace.project.domain.recruit.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitImageDto {
    private String url;
}
