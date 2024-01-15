package com.creavispace.project.domain.recruit.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitCreateRequestDto {

    private String kind;

    private int amount;

    private String proceedWay;

    private LocalDateTime startDay;

    private LocalDateTime endDay;

    private int workDay;

    private LocalDateTime end;

    private String contect;

    private String title;

    private String content;

    private String thumbnail;

    private long memberId;

    private List<RecruitPositionDto> positionList;

    private List<RecruitImageDto> imageList;
}
