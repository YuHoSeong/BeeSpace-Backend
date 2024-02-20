package com.creavispace.project.domain.recruit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitPositionResponseDto {
    private Long id;
    // enum으로 관리 변경
    private String position;
    private Integer amount;
    private Integer now;
}
