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
    private String postType;
    // enum으로 관리 변경
    private String category;
    private String title;
    private String content;
    private Integer amount;
    private Integer now;
    private List<RecruitTechStackResponseDto> techStacks;

}
