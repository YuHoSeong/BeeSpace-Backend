package com.creavispace.project.domain.recruit.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitReadResponseDto {
    private Long id;
    private Long memberId;
    private Integer viewCount;
    // enum으로 관리
    private String kind;
    // enum으로 관리
    private String contactWay;
    private String contact;
    private Integer amount;
    // enum으로 관리
    private String proceedWay;
    private int workDay;
    private LocalDateTime end;
    private String title;
    private String content;
    private List<RecruitPositionResponseDto> positionList;
}
