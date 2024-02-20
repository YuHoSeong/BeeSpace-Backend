package com.creavispace.project.domain.recruit.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitCreateRequestDto {

    // enum 관리로 변경
    private String kind;
    private int amount;
    private int workDay;
    private String contact;
    // enum 관리로 변경
    private String contactWay;
    // enum 관리로 변경
    private String proceedWay;
    private LocalDateTime end;
    private String title;
    private String content;
    private List<RecruitPositionRequestDto> recruitPositionList;
    private List<RecruitTechStackRequestDto> recruitTechStackList;
}
