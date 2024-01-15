package com.creavispace.project.domain.recruit.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitModifyRequestDto {

    private Long id;

    private String kind;

    private int amount;

    private String proceedWay;

    private LocalDateTime startDay;

    private LocalDateTime endDay;

    private int workDay;

    private String contect;

    private String title;

    private String content;

    private String thumbnail;

    private boolean status;
}
