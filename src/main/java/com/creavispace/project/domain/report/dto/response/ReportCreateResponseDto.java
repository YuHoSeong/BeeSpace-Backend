package com.creavispace.project.domain.report.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportCreateResponseDto {
    private Long postId;
    // enum으로 관리 변경
    private String postType;
    // enum으로 관리 변경
    private String reportKind;
    private String content;
}
