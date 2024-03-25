package com.creavispace.project.domain.recruit.dto.response;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String postType;
    private Long memberId;
    private String memberNickName;
    private String memberProfile;
    private Integer viewCount;
    // enum으로 관리
    private String category;
    // enum으로 관리
    private String contactWay;
    private String contact;
    private Integer amount;
    // enum으로 관리
    private String proceedWay;
    private int workDay;
    private LocalDate end;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<RecruitPositionResponseDto> positions;
    private List<RecruitTechStackResponseDto> techStacks;
    private String recruitViewLog;
}
