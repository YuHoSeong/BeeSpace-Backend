package com.creavispace.project.domain.recruit.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitDeleteRequestDto {
    private Long id;
}
