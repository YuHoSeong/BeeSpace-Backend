package com.creavispace.project.domain.recruit.dto.response;

import com.creavispace.project.domain.recruit.dto.request.RecruitImageDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitPositionRequestDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitCreateResponseDto {
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
