package com.creavispace.project.domain.recruit.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitPositionRequestDto {
    // enum 관리로 변경
    private String position;
    private int amount;
    private int now;
    
}
