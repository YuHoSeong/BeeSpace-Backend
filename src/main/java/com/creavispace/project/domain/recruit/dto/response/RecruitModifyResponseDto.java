package com.creavispace.project.domain.recruit.dto.response;

import com.creavispace.project.domain.recruit.dto.request.RecruitImageDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitPositionDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitModifyResponseDto {
    private Long id;

    private String memberNickname;

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

    private boolean status;

    private int viewCount;

    private List<RecruitPositionDto> positionList;

    private List<RecruitImageDto> imageList;
}

