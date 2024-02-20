package com.creavispace.project.domain.recruit.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitListReadResponseDto {
    private Long id;
    // enum으로 관리 변경
    private String kind;
    private String title;
    private String content;
    private Boolean bookmark;
    private Integer amount;
    private Integer now;
    private List<RecruitTechStackResponseDto> recruitTechStackList;

}
